package com.suraj.blogs.services;

public interface OtpService {
	public int generateOTP(String key);
	public int getOtp(String key);
	public void clearOTP(String key);

}
