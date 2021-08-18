package com.sseEmitterDemo.project.entities;

import java.io.Serializable;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private SseEmitter emitter;
	
}
