package com.telmoricardo.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.telmoricardo.cursomc.service.DBService;
import com.telmoricardo.cursomc.service.EmailService;
import com.telmoricardo.cursomc.service.SmtpEmailService;

@Configuration("hml")
public class HmlConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean isntatiateDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateTestDatabase();
		return true;
	}	
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
