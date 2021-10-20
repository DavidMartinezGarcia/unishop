package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, Integer> {
}
