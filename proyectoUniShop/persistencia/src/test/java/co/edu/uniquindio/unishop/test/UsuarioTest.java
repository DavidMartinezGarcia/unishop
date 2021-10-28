package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.TipoUsuario;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.repositorios.ProductoRepo;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;
    /**
     * En este método se crea un usuario, con el fin de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:usuarioPrueba.sql")
    public void registrarTest() {
        List<String> telefonos = new ArrayList<>();
        telefonos.add("2294194");
        telefonos.add("3175682908");
        Usuario usuario = new Usuario(Ciudad.BARRANCAMERMEJA, "Pedro Morales", "pedrom@gmail.com", telefonos, "pm2574-*", TipoUsuario.CLIENTE);
        usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioRepo.findById(1));
    }

    /**
     * En este método se elimina el usuario número 1 que esta en el repositorio usuarioPrueba.sql, con el fin de realizar
     * una prueba unitaria
     */
    @Test
    @Sql("classpath:usuarioPrueba.sql")
    public void eliminarTest() {
        usuarioRepo.deleteById(1);
        Usuario usuarioBuscado = usuarioRepo.findById(1).orElse(null);

        Assertions.assertNull(usuarioBuscado);
    }

    /**
     * En este método se actualiza el email del usuario número 1 que está en el repositorio usuarioPrueba.sql, con el fin
     * de realizar una prueba unitaria
     */
    @Test
    @Sql("classpath:usuarioPrueba.sql")
    public void actualizarTest() {
        Usuario usuGuardado = usuarioRepo.findById(1).orElse(null);
        usuGuardado.setEmail("anditocali@gmail.com");
        usuarioRepo.save(usuGuardado);
        Usuario usuarioBuscado = usuarioRepo.findById(1).orElse(null);
        Assertions.assertEquals("anditocali@gmail.com", usuarioBuscado.getEmail());
    }

    @Test
    @Sql("classpath:usuarioPrueba.sql")
    public void filtrarNombreTest() {
        List<Usuario> lista = usuarioRepo.findAllByNombreContains("Pablo");

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:usuarioPrueba.sql")
    public void filtrarEmailTest() {
        Optional<Usuario> usuario = usuarioRepo.findByEmail("pablochoa25@gmail.com");
        if (usuario.isPresent()) {
            System.out.println(usuario.get());
        } else {
            System.out.println("No existe ese correo");
        }
    }

    @Test
    @Sql("classpath:usuarioPrueba.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Usuario> lista = usuarioRepo.findAll(paginador);
        System.out.println(lista.stream().collect(Collectors.toList()));
    }

    @Test
    @Sql("classpath:usuarioPrueba.sql")
    public void ordenarListaTest(){
        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:usuarioPrueba.sql")
    @Sql("classpath:productosPrueba.sql")
    @Sql("classpath:favoritosPrueba.sql")
    public void obtenerFavoritosUsuario(){
        List<Producto> favoritos = usuarioRepo.obtenerProductoFavoritos("pablochoa25@gmail.com");
        favoritos.forEach(System.out::println);
        Assertions.assertEquals(3,favoritos.size());
    }
}
