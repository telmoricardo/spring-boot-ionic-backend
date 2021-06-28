package com.telmoricardo.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import com.telmoricardo.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
