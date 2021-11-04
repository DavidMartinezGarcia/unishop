package co.edu.uniquindio.unishop.test;

import co.edu.uniquindio.unishop.entidades.Categoria;
import co.edu.uniquindio.unishop.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    public void registrarTest(){
        Categoria categoria = new Categoria("Limpieza");
        Categoria guardado = categoriaRepo.save(categoria);
        Assertions.assertEquals(categoria, guardado);
    }

    @Test
    @Sql("classpath:categoriaPrueba.sql")
    public void eliminarTest(){
        categoriaRepo.deleteById(1);
        Categoria guardado = categoriaRepo.findById(1).orElse(null);
        Assertions.assertEquals(null, guardado);
    }

    @Test
    @Sql("classpath:categoriaPrueba.sql")
    public void actualizarTest(){
        Categoria guardado = categoriaRepo.findById(1).orElse(null);
        guardado.setNombre("Verduras");
        categoriaRepo.save(guardado);
        Categoria categoriaBuscada = categoriaRepo.findById(1).orElse(null);
        Assertions.assertEquals("Verduras",categoriaBuscada.getNombre());
    }

}
