package com.sseEmitterDemo.project.resources;

import java.io.IOException;
import java.time.LocalTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sseEmitterDemo.project.entities.Message;
import com.sseEmitterDemo.project.entities.User;
import com.sseEmitterDemo.project.repositories.UserRepository;

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {

	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public ResponseEntity<Message> sendMessage(@RequestBody Message message) {

		message.setInstant(LocalTime.now());
		
		for (User user: UserRepository.USERS) {
			try {
				if(!user.getName().equals(message.getUserName())) {
					user.getEmitter().send(SseEmitter.event().data(message));
				}
			} catch (IOException e) {
				user.getEmitter().complete();
				UserRepository.USERS.remove(user);
			}
		}
		
		return ResponseEntity.ok(message);

	}

}