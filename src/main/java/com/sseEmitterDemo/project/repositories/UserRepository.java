package com.sseEmitterDemo.project.repositories;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.sseEmitterDemo.project.entities.User;

@Repository
public interface UserRepository {

	public static final List<User> USERS = new CopyOnWriteArrayList<>();

	
}
