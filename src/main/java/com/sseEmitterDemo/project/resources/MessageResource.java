package com.sseEmitterDemo.project.resources;

import java.io.IOException;
import java.time.LocalTime;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sseEmitterDemo.project.entities.Message;
import com.sseEmitterDemo.project.entities.User;

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {



	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public void sendMessage(@RequestBody Message message) {

		message.setInstant(LocalTime.now());
		
		for (User user: UserResource.users) {
			try {
				user.getEmitter().send(SseEmitter.event().data(message));
			} catch (IOException e) {
				user.getEmitter().complete();
				UserResource.users.remove(user);
			}
		}

	}

}