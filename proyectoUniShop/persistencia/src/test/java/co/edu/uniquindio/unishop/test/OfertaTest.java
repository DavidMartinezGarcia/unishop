package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Oferta;
import co.edu.uniquindio.unishop.entidades.Subasta;
import co.edu.uniquindio.unishop.repositorios.OfertaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OfertaTest {

    @Autowired
    private OfertaRepo ofertaRepo;

    /**
     * En este método se crea una oferta, con el fin de realizar una prueba unitaria
     */
    @Test
    public void registrarTest(){

        Oferta oferta = new Oferta(1000);
        ofertaRepo.save(oferta);
        Assertions.assertNotNull(ofertaRepo.findById(1));

    }

    /**
     * En este método se elimina la oferta número 1 que esta en el repositorio ofertaPrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:ofertaPrueba.sql")
    public void eliminarTest(){

        ofertaRepo.deleteById(1);
        Oferta ofertaBuscada = ofertaRepo.findById(1).orElse(null);

        Assertions.assertNull(ofertaBuscada);

    }

    /**
     * En este método se actualiza el valor de la oferta número 1 que está en el repositorio ofertaPrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:ofertaPrueba.sql")
    public void actualizarTest(){

        Oferta ofertaGuardado = ofertaRepo.findById(1).orElse(null);
        ofertaGuardado.setValor(1200);
        ofertaRepo.save(ofertaGuardado);
        Oferta ofertaBuscada = ofertaRepo.findById(1).orElse(null);
        Assertions.assertEquals(1200, ofertaBuscada.getValor());

    }
}
