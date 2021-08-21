package com.sseEmitterDemo.project.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sseEmitterDemo.project.entities.User;
import com.sseEmitterDemo.project.repositories.UserRepository;
import com.sseEmitterDemo.project.services.UserService;

@RestController
@RequestMapping(value = "users/")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET, value = "/submit")
	public SseEmitter getEmitter(@RequestParam(name="name") String name) {

		SseEmitter emitter = new SseEmitter(-1L); //sem timeout
		
		UserRepository.USERS.forEach(user -> {
			try {
				user.getEmitter().send(SseEmitter.event().name("login").data(name));
			} catch (IOException e) {
				user.getEmitter().complete();
				UserRepository.USERS.remove(user);
			}
		});
		
		UserRepository.USERS.add(new User(name, emitter));

		return emitter;

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/isLogged")
	public ResponseEntity<Boolean> isLogged(@RequestParam(name = "name") String username){
		return ResponseEntity.ok(service.isOnline(username));
	}
	
	

}
