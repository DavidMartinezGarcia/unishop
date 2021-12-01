package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    @Query("select c from Categoria c where c.nombre = :nombre")
    Categoria buscarCategoriaNombre(String nombre);
}
