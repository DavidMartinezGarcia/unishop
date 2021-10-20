package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepo extends JpaRepository<Oferta, Integer> {
}
