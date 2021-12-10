package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Chat;
import co.edu.uniquindio.unishop.entidades.Mensaje;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.ChatServicio;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ChatBean {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private Chat chatSeleccionado;

    @Getter @Setter
    private List<Chat> chats;

    @Getter @Setter
    private ChatServicio chatServicio;
    @Getter @Setter
    private List<Mensaje> mensajesChatSeleccionado;

    @Getter @Setter
    private String nuevoMensaje;

    @Getter @Setter
    private List<Mensaje> mensajes;

    @Value(value = "#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar() throws Exception {
        this.chats = usuarioServicio.obtenerChatsUsuario(usuarioSesion.getCodigo());
        this.mensajes = new ArrayList<Mensaje>();
    }
    public void seleccionarChat(Usuario usuario){
        for(Chat temp:chats){
            if(temp.getUsuario().equals(usuario)){
                this.chatSeleccionado = temp;
                break;
            }
        }
    }

    public Mensaje getUltimoMensaje(Chat chat){
        Mensaje mensajeUltimo = null;
        if(chat.getMensajes().size()!=0){
        System.out.println("56 ultimo" + chat.getUsuario().getNombre());
        List<Mensaje> mensajes = chat.getMensajes();
        mensajeUltimo = mensajes.get(mensajes.size()-1);
        System.out.println(" 60" + mensajeUltimo.getFecha());
        }
        return mensajeUltimo;
    }

    public void cargarChat(){
        this.mensajesChatSeleccionado = chatSeleccionado.getMensajes();
    }

    public String obtenerNombreVendedor(Chat chat){
        return chat.getCodigoProducto().getProducto().getVendedor().getNombre();

    }

    public void agregarMensaje(){
        Mensaje mensaje1 = new Mensaje();
        mensaje1.setChat(chatSeleccionado);
        mensaje1.setFecha(LocalDate.now());
        mensaje1.setMensaje(nuevoMensaje);
        mensaje1.setChat(chatSeleccionado);
        chatSeleccionado.getMensajes().add(mensaje1);
        chatServicio.guardarMensaje(mensaje1);
        chatServicio.guardarChat(chatSeleccionado);
    }

    public List<Mensaje> seleccionarChat(Chat chat){
        this.chatSeleccionado = chat;
        for(Mensaje mensajeTemp: chat.getMensajes()){
            mensajes.add(mensajeTemp);
        }
        return mensajes;
    }

}
