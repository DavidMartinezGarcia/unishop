package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarTest(){
        Ciudad ciudad = new Ciudad("Roldanillo");
        ciudadRepo.save(ciudad);
        Assertions.assertNotNull(ciudadRepo.findById(4));
    }

    @Test
    @Sql("classpath:ciudadPrueba.sql")
    public void actualizarTest(){
        Ciudad ciudadGuardada = ciudadRepo.findById(1).orElse(null);
        ciudadGuardada.setNombre("Barrancabermeja");
        ciudadRepo.save(ciudadGuardada);

        Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);
        Assertions.assertEquals("Barrancabermeja", ciudadBuscada.getNombre());
    }

    @Test
    @Sql("classpath:ciudadPrueba.sql")
    public void eliminarTest(){
        ciudadRepo.deleteById(1);
        Ciudad ciudadBuscado = ciudadRepo.findById(1).orElse(null);
        Assertions.assertNull(ciudadBuscado);
    }

}
