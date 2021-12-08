package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Mail;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.MailService;
import co.edu.uniquindio.unishop.servicios.SendMailService;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;

@Component
@ViewScoped
public class recuperarContraBean implements Serializable {

    @Getter @Setter
    @Value("${spring.mail.host}")
    private String host;

    @Getter @Setter
    @Value("${spring.mail.port}")
    private int port;

    @Getter @Setter
    @Value("${spring.mail.username}")
    private String username;

    @Getter @Setter
    @Value("${spring.mail.password}")
    private String password;

    @Getter @Setter
    private String correo;

    @Getter @Setter
    private Integer identificiacion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private SendMailService correoServicio;

    @Autowired
    private MailService correoService;


    public void enviarCorreo(){

        if(!correo.trim().equals("")&&identificiacion!=null){
            try {
                Usuario usuario = usuarioServicio.recuperarContrasenia(correo, identificiacion);
                String password = usuario.getContrasenia();
                String email = usuario.getEmail();

                String mensaje = "UniShop te informa\n Tu contraseña es: "+password+"\nRecuerda que tu contraseña es privada y no debe ser compartida";

                correoService.sendSimpleMail(new Mail("uwu@gmail.com", email, "Recuperar Contraseña UniShop", mensaje, null, null));
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Mail enviado con exito");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos incorrectos");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
}
