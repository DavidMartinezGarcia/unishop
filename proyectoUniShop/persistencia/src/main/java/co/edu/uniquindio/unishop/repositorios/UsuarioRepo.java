package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    // @Query("select u from Usuario u where u.nombre = :nombre")
    // List<Usuario> obtenerUsuariosPorNombre(String nombre);

    List<Usuario> findAllByNombreContains(String nombre);

    Optional<Usuario> findByEmail(String email);

    //@Query("select u from Usuario u where u.email = :email and u.contrasenia = :contrasenia")
    //Optional<Usuario> verificarAutenticacion(String email, String contrasenia);

    Optional<Usuario> findByEmailAndContrasenia(String email, String contrasenia);

    Page<Usuario> findAll(Pageable paginador);
}
