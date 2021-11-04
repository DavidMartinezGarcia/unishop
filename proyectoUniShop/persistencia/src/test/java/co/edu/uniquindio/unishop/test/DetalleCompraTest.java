package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.*;
import co.edu.uniquindio.unishop.repositorios.CiudadRepo;
import co.edu.uniquindio.unishop.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
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
public class DetalleCompraTest {

    @Autowired
    private DetalleCompraRepo detalleRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CiudadRepo ciudadRepo;

    /**
     * En este método se crea un chat, con el fin de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:ciudadPrueba.sql")
    @Sql("classpath:usuarioPrueba.sql")
    public void registrarTest(){

        Date fechaLimite = new Date(2022, 5, 15);

        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);

        Usuario vendedor = usuarioRepo.findById(1).orElse(null);
        Producto producto = new Producto("Harina de maíz", "Harina de maíz especial para hacer arepas", 4500.0, 30, ciudad, 0, fechaLimite, vendedor);
        Compra compra = new Compra(MetodoPago.NEQUI, LocalDate.now());
        DetalleCompra detalleCompra = new DetalleCompra(producto, compra, 2);
          detalleRepo.save(detalleCompra);
        Assertions.assertNotNull(detalleRepo.findById(4).orElse(null));
    }

    /**
     * En este método se actualiza la cantidad del detalle compra número 1 que está en el repositorio detalleCompraPrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:detalleCompraPrueba.sql")
    public void actualizarTest(){

        DetalleCompra detalleGuardado = detalleRepo.findById(1).orElse(null);
        detalleGuardado.setCantidad(10);
        detalleRepo.save(detalleGuardado);
        DetalleCompra detalleBuscado = detalleRepo.findById(1).orElse(null);
        Assertions.assertEquals(10, detalleBuscado.getCantidad());
    }

    /**
     * En este método se elimina el detalle compra número 1 que esta en el repositorio detalleCompraPrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:detalleCompraPrueba.sql")
    public void eliminarTest(){

        detalleRepo.deleteById(1);
        DetalleCompra detalleBuscado = detalleRepo.findById(1).orElse(null);
        Assertions.assertNull(detalleBuscado);
    }
}
