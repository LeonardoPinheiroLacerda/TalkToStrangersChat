package com.sseEmitterDemo.project.services;

import org.springframework.stereotype.Service;

import com.sseEmitterDemo.project.entities.User;
import com.sseEmitterDemo.project.repositories.UserRepository;

@Service
public class UserService {
	
	public Boolean isOnline(String username){
		for(User user : UserRepository.USERS) {
			if(user.getName().equals(username))
				return true;
		}
		
		return false;
	}
	
}
