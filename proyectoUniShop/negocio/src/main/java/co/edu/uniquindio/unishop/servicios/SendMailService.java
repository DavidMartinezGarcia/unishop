package co.edu.uniquindio.unishop.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@Service
public class SendMailService {


    private JavaMailSender javaMailSender;

    public void sendMail(String hacia, String titulo, String mensaje){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        System.out.println("javaMailSender"+javaMailSender);
        System.out.println("mailMessage: "+mailMessage);
        mailMessage.setFrom("UniShop");
        mailMessage.setTo(hacia);
        mailMessage.setSubject(titulo);
        mailMessage.setText(mensaje);
        System.out.println("mailMessage2 : "+mailMessage);
        javaMailSender.send(mailMessage);
        System.out.println("javaMailSender"+javaMailSender);
    }
}
