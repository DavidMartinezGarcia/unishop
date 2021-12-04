package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.SendMailService;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class recuperarContraBean implements Serializable {

    @Getter @Setter
    private String correo;

    @Getter @Setter
    private Integer identificiacion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    private SendMailService correoServicio;


    public void enviarCorreo(){

        if(!correo.trim().equals("")&&identificiacion!=null){
            try {
                Usuario usuario = usuarioServicio.recuperarContrasenia(correo, identificiacion);
                String password = usuario.getContrasenia();
                String mensaje = "UniShop te informa\n Tu contraseña es: "+password+"\nRecuerda que tu contraseña es privada y no debe ser compartida";

                System.out.println("ABC: "+correoServicio);

                correoServicio.sendMail(correo, "Recuperacion de contraseña", mensaje);

                System.out.println("ABC2: "+correoServicio);

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Mail enviado con exito");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos incorrectos");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
}
