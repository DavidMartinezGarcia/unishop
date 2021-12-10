package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Chat;
import co.edu.uniquindio.unishop.entidades.Mensaje;
import co.edu.uniquindio.unishop.repositorios.ChatRepo;
import co.edu.uniquindio.unishop.repositorios.MensajeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServicioImpl implements ChatServicio{

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private MensajeRepo mensajeRepo;

    @Override
    public void guardarChat(Chat chat) {
        chatRepo.save(chat);
    }

    @Override
    public void guardarMensaje(Mensaje mensaje) {
        mensajeRepo.save(mensaje);
    }

    @Override
    public List<Chat> devolverChats() {
        return chatRepo.findAll();
    }


}
