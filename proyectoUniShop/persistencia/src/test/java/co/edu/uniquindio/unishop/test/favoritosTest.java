package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.repositorios.ProductoRepo;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class favoritosTest {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:usuarioPrueba.sql")
    @Sql("classpath:productosPrueba.sql")
    @Sql("classpath:favoritosPrueba.sql")
    public void obtenerFavoritosUsuarioTest(){

        Usuario usuario = usuarioRepo.findById(2).orElse(null);
        System.out.println(usuario.getListaFavoritos().size());
        List<Producto> favoritos = usuarioRepo.obtenerProductoFavoritos("juanperez134@gmail.com");
        System.out.println(favoritos.size());
        Assertions.assertEquals(2, favoritos.size());
     }
}
