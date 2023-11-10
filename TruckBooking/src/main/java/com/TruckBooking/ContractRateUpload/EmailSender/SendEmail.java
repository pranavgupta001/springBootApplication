package com.TruckBooking.ContractRateUpload.EmailSender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

//An object of this class can be made anywhere for sending simple mails to anyone.

@Slf4j
@Component
public class SendEmail {
    @Autowired
    private JavaMailSender javaMailSender;
    public boolean isSend;

    public void send(String address, String subject, String body){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
        try{
            helper.setTo(address);
            helper.setSubject(subject);
            helper.setText(body,false);
            javaMailSender.send(message);
            isSend=true;
            log.info("Mail Sent to: "+address);
        }
        catch (Exception e){
            isSend=false;
            log.error("Mail not Sent to: "+address);
        }
    }

}
