package com.sseEmitterDemo.project.resources;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sseEmitterDemo.project.entities.Message;
import com.sseEmitterDemo.project.entities.User;

@RestController
@RequestMapping(value = "/message")
public class MessageResource {

	private List<User> users = new CopyOnWriteArrayList<>();

	@RequestMapping(method = RequestMethod.GET, value = "/emitte")
	public SseEmitter getEmitter(@RequestParam(name="name") String name) {

		SseEmitter emitter = new SseEmitter(-1L); //sem timeout
		
		users.add(new User(name, emitter));

		return emitter;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public void sendMessage(@RequestBody String message) {

		for (User user: users) {
			try {
				user.getEmitter().send(SseEmitter.event().data(new Message(user.getName(), message, LocalTime.now())).name("message").reconnectTime(1000L));
			} catch (IOException e) {
				System.out.println(users.size());
				user.getEmitter().complete();
				users.remove(user);
				System.out.println(users.size());
			}
		}

	}

}