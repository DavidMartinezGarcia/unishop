package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Mail;

public interface MailService {
    public Mail sendSimpleMail(Mail mail) throws Exception;
}
