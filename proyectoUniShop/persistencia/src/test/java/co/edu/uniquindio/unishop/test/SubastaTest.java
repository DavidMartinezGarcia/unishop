package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Subasta;
import co.edu.uniquindio.unishop.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaTest {

    @Autowired
    private SubastaRepo subastaRepo;

    /**
     * En este método se crea una subasta, con el fin de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:subastaPrueba.sql")
    public void registrarTest(){
        Date fechaLimite = new Date(2022, 5, 15);
        Producto producto = new Producto("Harina de maíz", "Harina de maíz especial para hacer arepas", 4500.0, 30, Ciudad.BELLO, 0, fechaLimite);
        Subasta subasta = new Subasta(producto, 2);
        subastaRepo.save(subasta);
        Assertions.assertNotNull(subastaRepo.findById(1));
    }

    /**
     * En este método se elimina la subasta número 1 que esta en el repositorio subastaPrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:subastaPrueba.sql")
    public void eliminarTest(){
        subastaRepo.deleteById(1);
        Subasta subastaBuscada = subastaRepo.findById(1).orElse(null);
        Assertions.assertNull(subastaBuscada);
    }

    /**
     * En este método se actualiza el tiempo limite de la subasta número 1 que está en el repositorio subastaPrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:subastaPrueba.sql")
    public void actualizarTest(){
        Subasta subGuardada = subastaRepo.findById(1).orElse(null);
        subGuardada.setTiempoLimite(5);
        subastaRepo.save(subGuardada);
        Subasta subastaGuardada = subastaRepo.findById(1).orElse(null);
        Assertions.assertEquals(5, subastaGuardada.getTiempoLimite());
    }
}
