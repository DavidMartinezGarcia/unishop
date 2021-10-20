package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubastaRepo extends JpaRepository<Subasta, Integer> {
}
