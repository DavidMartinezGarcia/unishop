package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Chat;
import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.nombre = :nombre")
    List<Usuario> obtenerUsuariosPorNombre(String nombre);

    List<Usuario> findAllByNombreContains(String nombre);

    Optional<Usuario> findByEmail(String email);

    @Query("select u from Usuario u where u.email = :email and u.contrasenia = :contrasenia")
    Optional<Usuario> verificarAutenticacion(String email, String contrasenia);

    Optional<Usuario> findByEmailAndContrasenia(String email, String contrasenia);

    //Consulta para obtener un usuario dado su correo y codigo
    Optional<Usuario> findByEmailAndCodigo(String email, Integer codigo);

    Page<Usuario> findAll(Pageable paginador);

    //La lista del IN siempre va entre parentesis y el IN y JOIN solo funcionan cunado quiero relacionar un objeto con una lista
    @Query("select p from Usuario u, IN (u.listaFavoritos) p where u.email = :email")
    List<Producto> obtenerProductoFavoritos(String email);

    //Consulta para obtener los productos de un ususario dado su codigo
    @Query("select u.listaFavoritos from Usuario u where u.codigo = :codigo")
    List<Producto> obtenerProductosFavoritos(Integer codigo);

    //Consulta para obtener los productos comprados por un usuario
    @Query("select p from Usuario u, IN (u.compras) p where u.codigo = :codigoUsuario")
    List<Compra> obtenerCompras(Integer codigoUsuario);

    @Query("select u.chats from Usuario u where u.codigo = :codigo")
    List<Chat> obtenerChatsUsuario(Integer codigo);

    //Consulta para obtener los usuarios que NO son administradores
    @Query("select u from Usuario u where u.tipoUsuario.nombre <> 'admin'")
    List<Usuario> obtenerUsuariosMortales();
}
