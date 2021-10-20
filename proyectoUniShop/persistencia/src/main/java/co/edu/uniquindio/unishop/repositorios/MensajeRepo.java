package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepo extends JpaRepository<Mensaje, Integer> {
}
