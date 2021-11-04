package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.*;
import co.edu.uniquindio.unishop.repositorios.CiudadRepo;
import co.edu.uniquindio.unishop.repositorios.ComentarioRepo;
import co.edu.uniquindio.unishop.repositorios.TipoUsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private TipoUsuarioRepo tipoUsuarioRepo;

    /**
     * En este método se crea un comentario, con el fin de realizar una prueba unitaria
     */

    @Test
    @Sql("classpath:ciudadPrueba.sql")
    @Sql("classpath:tipoUsuarioPrueba.sql")
    public void registrarTest(){

        List<String> telefonos = new ArrayList<>();
        Ciudad ciudad = ciudadRepo.getById(1);
        TipoUsuario tipoUsuario = tipoUsuarioRepo.getById(1);
        telefonos.add("2294194");
        telefonos.add("3175682908");

        Date fecha = new Date(2030, 12,12);
        Usuario usuario = new Usuario(ciudad, "Santiago perez", "sp05@gamiil.com", telefonos, "sp12345", tipoUsuario);
        Producto producto = new Producto("Alcachofas", "Se come bien rico", 12000.0, 20, ciudad, 0, fecha, usuario);
        Comentario comentario = new Comentario(producto, usuario, "Esta bonito",5, LocalDate.now());

        comentarioRepo.save(comentario);
        Assertions.assertNotNull(comentarioRepo.findById(1));
    }
    /**
     * En este método se actualiza el mensaje del comentario número 1 que está en el repositorio comentarioPrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:comentarioPrueba.sql")
    public void actualizarTest() {

        Comentario comentarioGuardado = comentarioRepo.findById(1).orElse(null);
        comentarioGuardado.setComentario("Ahora esta mejor");
        comentarioRepo.save(comentarioGuardado);

        Comentario comentarioBuscado = comentarioRepo.findById(1).orElse(null);
        Assertions.assertEquals("Ahora esta mejor", comentarioBuscado.getComentario());
    }

    /**
     * En este método se elimina el comentario número 1 que esta en el repositorio comentarioPrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:comentarioPrueba.sql")
    public void eliminarTest(){

        comentarioRepo.deleteById(1);
        Comentario comentarioBuscado = comentarioRepo.findById(1).orElse(null);
        Assertions.assertNull(comentarioBuscado);

    }
}
