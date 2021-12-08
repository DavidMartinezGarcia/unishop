package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Chat;
import co.edu.uniquindio.unishop.repositorios.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServicioImpl implements ChatServicio{

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public void guardarChat(Chat chat) {
        chatRepo.save(chat);
    }
}
