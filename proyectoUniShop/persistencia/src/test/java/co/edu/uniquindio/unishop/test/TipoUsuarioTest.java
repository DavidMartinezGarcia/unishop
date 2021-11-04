package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Categoria;
import co.edu.uniquindio.unishop.entidades.TipoUsuario;
import co.edu.uniquindio.unishop.repositorios.TipoUsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TipoUsuarioTest {

    @Autowired
    private TipoUsuarioRepo tipoUsuarioRepo;

    @Test
    public void registrarTest(){
        TipoUsuario tipoUsuario = new TipoUsuario("Administrador");
        TipoUsuario guardado = tipoUsuarioRepo.save(tipoUsuario);
        Assertions.assertEquals(tipoUsuario, guardado);
    }

    @Test
    @Sql("classpath:tipoUsuarioPrueba.sql")
    public void eliminarTest(){
        tipoUsuarioRepo.deleteById(1);
        TipoUsuario guardado = tipoUsuarioRepo.findById(1).orElse(null);
        Assertions.assertEquals(null, guardado);
    }

    @Test
    @Sql("classpath:categoriaPrueba.sql")
    public void actualizarTest(){
        TipoUsuario guardado = tipoUsuarioRepo.findById(1).orElse(null);
        guardado.setNombre("Programador");
        tipoUsuarioRepo.save(guardado);
        TipoUsuario tipoUsuarioBuscado = tipoUsuarioRepo.findById(1).orElse(null);
        Assertions.assertEquals("Programador",tipoUsuarioBuscado.getNombre());
    }
}
