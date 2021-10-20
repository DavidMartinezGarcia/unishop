package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Comentario;
import co.edu.uniquindio.unishop.repositorios.MensajeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import co.edu.uniquindio.unishop.entidades.*;

import java.time.LocalDate;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    /**
     * En este método se crea un mensaje, con el fin de realizar una prueba unitaria
     */
    @Test
    public void registrarTest(){

        Mensaje mensaje= new Mensaje(LocalDate.now(), "El producto esta muy feo");

        mensajeRepo.save(mensaje);
        Assertions.assertNotNull(mensajeRepo.findById(1));
    }

    /**
     * En este método se actualiza el mensaje del mensaje número 1 que está en el repositorio mensajePrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:mensajePrueba.sql")
    public void actualizarTest(){

        Mensaje mensajeGuardado = mensajeRepo.findById(1).orElse(null);
        mensajeGuardado.setMensaje("Ya no, ya lo veo feo");
        mensajeRepo.save(mensajeGuardado);

        Mensaje mensajeBuscado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertEquals("Ya no, ya lo veo feo", mensajeBuscado.getMensaje());
    }

    /**
     * En este método se elimina el mensaje número 1 que esta en el repositorio mensajePrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:mensajePrueba.sql")
    public void eliminarTest(){

        mensajeRepo.deleteById(1);
        Mensaje mensajeBuscado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertNull(mensajeBuscado);
    }

}
