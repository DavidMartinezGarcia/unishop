package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
}
