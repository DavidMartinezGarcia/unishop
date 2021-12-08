package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    void eliminarUsuario(Integer codigo) throws  Exception;

    List<Usuario> listarUsuarios();

    List<Producto> listarFavoritos(String email) throws Exception;

    List<Producto> obtenerFavoritos(Integer codigo) throws  Exception;

    void agregarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    Usuario obtenerUsuario(Integer id) throws Exception;

    Usuario iniciarSesion(String email, String password) throws Exception;

    Usuario recuperarContrasenia(String email, Integer codigo) throws  Exception;
}
