package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());

        if(buscado.isPresent()){
            throw new Exception("El codigo del usuario ya existe");
        }
        buscado = buscarPorEmail(u.getEmail());
        if(buscado.isPresent()){
            throw new Exception("El email del usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    private Optional<Usuario> buscarPorEmail(String email) throws Exception {
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById((u.getCodigo()));

        if(buscado.isEmpty()){
            throw new Exception("El usuario no existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public void eliminarUsuario(Integer codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El codigo del usuario no existe");
        }
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Producto> listarFavoritos(String email) throws Exception {
        Optional<Usuario> buscado = buscarPorEmail(email);

        if(buscado.isEmpty()){
            throw new Exception("El email del usuario ya existe");
        }
        return usuarioRepo.obtenerProductoFavoritos(email);
    }

    @Override
    public Usuario obtenerUsuario(Integer id) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(id);

        if(buscado.isEmpty()){
            throw new Exception("El usuario NO existe");
        }
        return buscado.get();
    }

    public Usuario iniciarSesion(String email, String password) throws Exception{
        return usuarioRepo.findByEmailAndContrasenia(email,password).orElseThrow( () -> new Exception("Los datos de autenticacion son incorrectos"));
    }
}
