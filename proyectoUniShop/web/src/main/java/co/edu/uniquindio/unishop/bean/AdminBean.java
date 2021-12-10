package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.TipoUsuario;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.repositorios.TipoUsuarioRepo;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Component

public class AdminBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private List<Usuario> usuarios;

    @PostConstruct
    public void inicializar(){
        try {
            usuarios = usuarioServicio.listarUsuariosMortales();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void volverAdmin(Usuario u){

        try{

            u.setTipoUsuario(usuarioServicio.obtenerTipoUsuario("admin"));
            usuarioServicio.actualizarUsuario(u);
        }catch(Exception e){

        }
    }
    public String eliminarUsuario(Integer codigo){
        try {
            usuarioServicio.eliminarUsuario(codigo);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Usuario eliminado con exito");
            FacesContext.getCurrentInstance().addMessage("mis-usuarios", fm);
            return "/usuario/usuarios?faces-redirect=true";

        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mis-usuarios", fm);
            e.printStackTrace();
        }
        return null;
    }


}
