package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    @Query("select c from Comentario c where c.puntuacion between :calificacionMenor and :calificacionMayor")
    List<Comentario> listarComentariosRango(int calificacionMenor, int calificacionMayor);
}
