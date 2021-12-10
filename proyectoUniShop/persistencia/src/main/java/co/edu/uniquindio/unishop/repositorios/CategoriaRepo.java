package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Categoria;
import co.edu.uniquindio.unishop.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    @Query("select c from Categoria c where c.nombre = :nombre")
    Categoria buscarCategoriaNombre(String nombre);


}
