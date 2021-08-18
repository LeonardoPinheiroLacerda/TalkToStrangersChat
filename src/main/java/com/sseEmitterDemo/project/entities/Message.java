package com.sseEmitterDemo.project.entities;

import java.io.Serializable;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class Message  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String userName;
	private String message;
	private LocalTime instant;
	
}
