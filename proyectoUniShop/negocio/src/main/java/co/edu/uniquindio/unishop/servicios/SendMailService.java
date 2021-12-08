package co.edu.uniquindio.unishop.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String body, String topic){


        System.out.println("Entro 1");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("uwu@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);
        System.out.println("antes de petar: "+to);
        try{
            javaMailSender.send(simpleMailMessage);
        }catch(Exception e){
            e.printStackTrace();
        }


        System.out.println("Sent email uwu");
    }


}
