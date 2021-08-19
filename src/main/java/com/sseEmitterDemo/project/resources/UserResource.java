package com.sseEmitterDemo.project.resources;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sseEmitterDemo.project.entities.User;

@RestController
@RequestMapping(value = "users/")
public class UserResource {
	
	public static List<User> users = new CopyOnWriteArrayList<>();

	@RequestMapping(method = RequestMethod.GET, value = "/submit")
	public SseEmitter getEmitter(@RequestParam(name="name") String name) {

		SseEmitter emitter = new SseEmitter(-1L); //sem timeout
		
		users.forEach(user -> {
			try {
				user.getEmitter().send(SseEmitter.event().name("login").data(name));
			} catch (IOException e) {
				user.getEmitter().complete();
				users.remove(user);
			}
		});
		
		users.add(new User(name, emitter));

		return emitter;

	}

}
