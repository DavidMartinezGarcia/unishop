package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Mail sendSimpleMail(Mail mail) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        message.setTo(mail.getTo());
        message.setSentDate(mail.getSendDate());
        try {
            javaMailSender.send(message);

        } catch (MailException e) {
            e.printStackTrace();
            throw new Exception("Error al enviar el correo electronico");
        }
        return mail;
    }
}
