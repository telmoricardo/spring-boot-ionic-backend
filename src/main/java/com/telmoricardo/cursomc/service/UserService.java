package com.telmoricardo.cursomc.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.telmoricardo.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
		}
	}
}
