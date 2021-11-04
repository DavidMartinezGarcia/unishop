package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    //Optional<Ciudad> findByNombre(String nombreCiudad);

    @Query("select u from Ciudad c join c.usuarios u where c.nombre = :nombre")
    List<Usuario> listarUsuarios(String nombre);


}
