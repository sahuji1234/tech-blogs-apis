package com.suraj.blogs.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blogs.services.impl.EmailServiceImpl;
import com.suraj.blogs.services.impl.OtpServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class OtpController {
	
	@Autowired
	private OtpServiceImpl otpService;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@GetMapping("/generateOtp")
	public String generateOTP(@RequestParam("username") String username) {
		// generate otp
		int otp = otpService.generateOTP(username);
		//send otp
		try {
			emailService.sendOtpMessage(username, "one-time-password", "Hello "+username+" your otp is "+otp+" vaild for 5 minutes");
		    return "success";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "failed";
		}
		
	}
	
	@RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
	public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum,@RequestParam("username") String username){
		
		final String SUCCESS = "Entered Otp is valid";
		final String FAIL = "Entered Otp is NOT valid. Please Retry!";
		if(otpnum >= 0){
			
			  int serverOtp = otpService.getOtp(username);
			  System.out.println("server opt="+serverOtp);
			    if(serverOtp > 0){
			      if(otpnum == serverOtp){
			    	 
			          otpService.clearOTP(username);
			
	                  return (SUCCESS);
	                }  else {
	                    return FAIL;
	                   }
	               }else {
	              return FAIL;
	               }
	             }else {
	                return FAIL;
	         }
	}


}
