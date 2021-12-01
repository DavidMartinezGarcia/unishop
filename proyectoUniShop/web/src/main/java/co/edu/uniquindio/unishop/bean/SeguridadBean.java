package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

 @Getter @Setter
 private boolean autenticado;

 @Getter @Setter
 private String email, password;

 @Getter @Setter
 private Usuario usuarioSesion;

 @Autowired
 private UsuarioServicio usuarioServicio;

 public String iniciarSesion(){
  if(!email.isEmpty() && !password.isEmpty()){
   try {
    usuarioSesion = usuarioServicio.iniciarSesion(email, password);
    autenticado = true;
    return "index?faces-redirect=true";
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
  return null;
 }

 public String cerrarSesion(){
  FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
  return "index?faces-redirect = true";
 }

}
