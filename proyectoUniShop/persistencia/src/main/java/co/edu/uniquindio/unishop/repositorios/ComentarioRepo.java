package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {
}
