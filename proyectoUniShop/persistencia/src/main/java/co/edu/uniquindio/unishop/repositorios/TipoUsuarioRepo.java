package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoUsuarioRepo extends JpaRepository<TipoUsuario, Integer> {

    //Consulta que devuelve el tipo de usuario dado su nombre
    @Query("select t from TipoUsuario t where t.nombre = :nombre")
    TipoUsuario obtenerTipoUsuario(String nombre);

}
