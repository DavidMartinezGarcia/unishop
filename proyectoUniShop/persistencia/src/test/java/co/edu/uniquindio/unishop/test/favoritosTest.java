package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Usuario;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.repositorios.ProductoRepo;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FavoritosTest {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;


    @Test
    @Sql("classpath:usuarioPrueba.sql")
    @Sql("classpath:productosPrueba.sql")
    public void registrarFavorito (){

     Usuario usuario = usuarioRepo.findById(1).orElse(null);

     List<Producto> favoritosTest = new ArrayList<Producto>();

     favoritosTest.add(productoRepo.findById(2).orElse(null));
     favoritosTest.add(productoRepo.findById(3).orElse(null));

     usuario.setListaFavoritos(favoritosTest);

     usuarioRepo.save(usuario);

     Usuario encontrado = usuarioRepo.getById(1);
     Assertions.assertEquals(2, encontrado.getListaFavoritos().size());
    }
}
