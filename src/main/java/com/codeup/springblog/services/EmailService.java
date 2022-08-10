package com.codeup.springblog.services;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String from;

    public void prepareAndSend(Post post, String subject) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(post.getUsers().getEmail());
        msg.setSubject(subject);
        msg.setText(String.format("Your post has the following: %n Title:  %s%n Body: %s",post.getName(),post.getBody()));

        try {
            this.emailSender.send(msg);
        }catch (MailException e) {
            System.err.println(e.getMessage());
        }
    }
}
