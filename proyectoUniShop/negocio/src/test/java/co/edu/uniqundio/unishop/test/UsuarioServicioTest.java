package co.edu.uniqundio.unishop.test;

import co.edu.uniquindio.unishop.NegocioApplication;
import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.TipoUsuario;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ProductoServicio productoServicio;

    @Test
    public void registrarUsuarioTest(){
        Ciudad ciudad = new Ciudad("Armenia");
        List<String> telefonos = new ArrayList<>();
        telefonos.add("73333333");
        TipoUsuario tipo = new TipoUsuario("Usuario");
        Usuario u = new Usuario(123,ciudad, "Paco", "paco@mail.com", telefonos, "1234222", tipo);

        try{
            Usuario respuesta = usuarioServicio.registrarUsuario(u);
            System.out.println(respuesta);
            Assertions.assertNotNull(respuesta);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void eliminar(){
        try {
            usuarioServicio.eliminarUsuario(123);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
    @Test
    public void listar() throws Exception{
       List<Usuario> lista = usuarioServicio.listarUsuarios();
       lista.forEach(System.out::println);
    }
    @Test
    public void actualizar(){

        try{
            Usuario u = usuarioServicio.obtenerUsuario(1006290156);
            u.setContrasenia("askldfjñlasd");
            Usuario respuesta = usuarioServicio.actualizarUsuario(u);
            Assertions.assertNotNull(respuesta);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void agregarFavoritos(){

        try{
            Usuario u = usuarioServicio.obtenerUsuario(1006290156);
            Producto p = productoServicio.obtenerProducto(1);
            usuarioServicio.agregarProductoFavorito(p,u);

        }catch(Exception e){
           e.printStackTrace();
        }

    }

    //INCOMPLETO
    public void loginTest(){
        try{

        }catch(Exception e){
            Assertions.assertTrue(false, e.getMessage());
        }

    }

}
