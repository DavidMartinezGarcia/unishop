package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {
}
