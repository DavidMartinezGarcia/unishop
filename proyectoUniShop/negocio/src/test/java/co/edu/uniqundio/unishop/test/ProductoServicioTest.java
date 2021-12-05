package co.edu.uniqundio.unishop.test;

import co.edu.uniquindio.unishop.NegocioApplication;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    //INCOMPLETO
    @Test
    public void obtenerProductoTest(){
        try{

        }catch(Exception e){
            Assertions.assertTrue(false, e.getMessage());
        }
    }
    @Test
    public void agregarFavoritos(){

        try{
            Usuario u = usuarioServicio.obtenerUsuario(1006290156);
            Producto p = productoServicio.obtenerProducto(1);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(p);
            //productoServicio.agregarProductoFavorito(p,u);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
