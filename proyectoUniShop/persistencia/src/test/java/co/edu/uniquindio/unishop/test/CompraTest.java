package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.MetodoPago;
import co.edu.uniquindio.unishop.repositorios.CompraRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    /**
     * En este método se crea una compra, con el fin de realizar una prueba unitaria
     */
    @Test
    public void registrarTest() {

        Compra compra = new Compra(MetodoPago.NEQUI, LocalDate.now());
        compraRepo.save(compra);
        Assertions.assertNotNull(compraRepo.findById(1));
    }

    /**
     * En este método se elimina la compra número 1 que esta en el repositorio compraPrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:compraPrueba.sql")
    public void eliminarTest() {

        compraRepo.deleteById(1);
        Compra compraBuscada = compraRepo.findById(1).orElse(null);

        Assertions.assertNull(compraBuscada);

    }

    /**
     * En este método se actualiza el metodo de pago de la compra número 1 que está en el repositorio compraPrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:compraPrueba.sql")
    public void actualizarTest() {

        Compra compraGuardada = compraRepo.findById(1).orElse(null);
        compraGuardada.setMetodoDePago(MetodoPago.DAVIPLATA);
        compraRepo.save(compraGuardada);

        Compra compraBuscada = compraRepo.findById(1).orElse(null);

        Assertions.assertEquals(MetodoPago.DAVIPLATA, compraBuscada.getMetodoDePago());

    }
}