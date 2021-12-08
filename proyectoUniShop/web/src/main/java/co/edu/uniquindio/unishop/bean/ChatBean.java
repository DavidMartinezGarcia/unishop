package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Chat;
import co.edu.uniquindio.unishop.entidades.Mensaje;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class ChatBean {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private Chat chatSeleccionado;

    @Getter @Setter
    private List<Chat> chats;

    @Getter @Setter
    private List<Mensaje> mensajesChatSeleccionado;

    @Value(value = "#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    public void seleccionarChat(Usuario usuario){
        for(Chat temp:chats){
            if(temp.getUsuario().equals(usuario)){
                this.chatSeleccionado = temp;
                break;
            }
        }
    }

    public void cargarChat(){
        this.mensajesChatSeleccionado = chatSeleccionado.getMensajes();
    }

    public boolean perteneceAlUsuario(Usuario user){
        boolean centinela = false;
        if(user.equals(usuarioSesion)){
            centinela = true;
        }
        return centinela;
    }

    public Mensaje getUltimoMensaje(List<Mensaje> mensajes){

        Mensaje mensajeUltimo= mensajes.get(mensajes.size()-1);
        return mensajeUltimo;
    }

}
