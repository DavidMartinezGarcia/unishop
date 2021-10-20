package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {
}
