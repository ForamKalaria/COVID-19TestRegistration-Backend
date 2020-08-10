package com.example.controller;

import javax.mail.MessagingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.model.MailMessage;
import com.example.service.MailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RegistrationController {

	@Autowired
	private MailService notificationService;
	
	@Autowired
	private MailMessage message;
	
	@PostMapping(value = "/send-mail")
    @PreAuthorize("permitAll()")
    public String send(@Valid @RequestBody MailMessage mail) {

        MailMessage message = new MailMessage(mail.getEmailAddress(),
                                                mail.getSubject(),
                                                mail.getBodyText());

        try {
            notificationService.sendEmail(message);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }


		
		/*
		 * Creating a MailMessage with the help of MailMessage class that we have declared. Setting
		 * the First,Last and Email address of the sender.
		 */
//		
//		message.setEmailAddress(getEmail); // Receiver's email address
//		message.setSubject("test");m
//		message.setBodyText("You have done a good job!");
		/*
		 * Here we will call sendEmail() for Sending mail to the sender.
		 */
//		try {
//			notificationService.sendEmail(message);
//		} catch (MailException mailException) {
//			System.out.println(mailException);
//		}
//		return "Congratulations! Your mail has been send to the user.";


	/**
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/send-mail-attachment")
	@PreAuthorize("permitAll()")
	public String sendWithAttachment(@RequestBody MailMessage message) throws MessagingException {

		/*
		 * Creating a MailMessage with the help of MailMessage class that we have declared. Setting
		 * the First,Last and Email address of the sender.
		 */

		message.setEmailAddress("kalariabakul@gmail.com");// Receiver's email address
		message.setSubject("testAttachement");
		message.setBodyText("You have done a good job! \n Please find attached file");
		/*
		 * Here we will call sendEmailWithAttachment() for Sending mail to the sender
		 * that contains a attachment.
		 */
		try {
			notificationService.sendEmailWithAttachment(message);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
}
