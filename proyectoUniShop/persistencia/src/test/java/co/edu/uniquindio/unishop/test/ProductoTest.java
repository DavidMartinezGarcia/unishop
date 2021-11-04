package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.repositorios.CiudadRepo;
import co.edu.uniquindio.unishop.repositorios.ProductoRepo;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CiudadRepo ciudadRepo;

    /**
     * En este método se crea un producto, con el fin de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:usuarioPrueba.sql")
    @Sql("classpath:ciudadarioPrueba.sql")
    public void registrarTest(){

        Usuario vendedor = usuarioRepo.findById(1).orElse(null);

        Ciudad ciudad = ciudadRepo.findById(2).orElse(null);

        Date fecha = new Date(2030, 12,12);
        Producto producto = new Producto("Alcachofas", "Se come bien rico", 12000.0, 20, ciudad, 0, fecha, vendedor);
        productoRepo.save(producto);
        Assertions.assertNotNull(productoRepo.findById(1));

    }

    /**
     * En este método se elimina el producto número 1 que esta en el repositorio productosPrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:productoPrueba.sql")
    public void eliminarTest(){

        productoRepo.deleteById(1);
        Producto productoBuscado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(productoBuscado);

    }

    /**
     * En este método se actualiza la descripcion del producto número 1 que está en el repositorio productosPrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:productoPrueba.sql")
    public void actualizarTest(){

        Producto proGuardado = productoRepo.findById(1).orElse(null);
        proGuardado.setDescripcion("Se hace sopa con ella");
        productoRepo.save(proGuardado);

        Producto productoBuscado = productoRepo.findById(1).orElse(null);

        Assertions.assertEquals("Se hace sopa con ella", productoBuscado.getDescripcion());

    }

    @Test
    @Sql("classpath:usuarioPrueba.sql")
    @Sql("classpath:productoPrueba.sql")
    public void obtenerNombreVendedorTest(){
        String nombre = productoRepo.obtenerNombreVendedor(1);
        Assertions.assertEquals("Pablo Ochoa", nombre);
    }
}
