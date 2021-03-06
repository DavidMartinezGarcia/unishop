package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Chat;
import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.entidades.TipoUsuario;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.repositorios.ChatRepo;
import co.edu.uniquindio.unishop.repositorios.CiudadRepo;
import co.edu.uniquindio.unishop.repositorios.TipoUsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private TipoUsuarioRepo tipoUsuarioRepo;

    /**
     * En este método se crea un chat, con el fin de realizar una prueba unitaria
     */
    @Test
    public void registrarTest(){

        Chat chat = new Chat();
        chatRepo.save(chat);
        Assertions.assertNotNull(chatRepo.findById(1));

    }

    /**
     * En este método se elimina el chat número 1 que esta en el repositorio chatPrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:chatPrueba.sql")
    public void eliminarTest(){

        chatRepo.deleteById(1);
        Chat chatBuscado = chatRepo.findById(1).orElse(null);

        Assertions.assertNull(chatBuscado);

    }

    /**
     * En este método se actualiza el usuario del chat número 1 que está en el repositorio chatPrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:ciudadPrueba.sql")
    @Sql("classpath:tipoUsuarioPrueba.sql")
    @Sql("classpath:chatPrueba.sql")
    public void actualizarTest(){

        Ciudad ciudadGuardado = ciudadRepo.findById(2).orElse(null);
        Chat chatGuardado = chatRepo.findById(1).orElse(null);
        TipoUsuario tipo = tipoUsuarioRepo.findById(1).orElse(null);

        chatGuardado.setUsuario(new Usuario(ciudadGuardado, "Diego Armando", "@gmail", null, "1234", tipo ));
        chatRepo.save(chatGuardado);

        Chat chatBuscado = chatRepo.findById(1).orElse(null);
        Assertions.assertEquals("Diego Armando", chatBuscado.getUsuario().getNombre());
    }
}
