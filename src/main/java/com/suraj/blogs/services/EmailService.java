package com.suraj.blogs.services;

import javax.mail.MessagingException;

public interface EmailService {
	public void sendOtpMessage(String to, String subject,String message) throws MessagingException;
}
