package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
}
