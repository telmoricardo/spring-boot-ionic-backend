package com.telmoricardo.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.telmoricardo.cursomc.service.DBService;

@Configuration("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean isntatiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	

}
