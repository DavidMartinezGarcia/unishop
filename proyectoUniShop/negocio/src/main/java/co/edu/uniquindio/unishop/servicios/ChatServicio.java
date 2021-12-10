package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Chat;
import co.edu.uniquindio.unishop.entidades.Mensaje;

import java.util.List;

public interface ChatServicio {
    void guardarChat(Chat chat);
    void guardarMensaje(Mensaje mensaje);
    List<Chat> devolverChats();
  //
}
