package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Chat;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.TipoUsuario;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.proyecciones.UsuarioBase;
import co.edu.uniquindio.unishop.repositorios.TipoUsuarioRepo;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    @Autowired
    private TipoUsuarioRepo tipoUsuarioRepo;

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

    @Override
    public TipoUsuario obtenerTipoUsuario(String nombre) throws Exception{

        return tipoUsuarioRepo.obtenerTipoUsuario(nombre);
    }

    private Optional<Usuario> buscarPorEmail(String email) throws Exception {
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById((u.getCodigo()));

        System.out.println("Servicio: "+ u.getListaFavoritos().size());
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

    public List<UsuarioBase> listarUsuariosBase(){
        return null;
    }

    @Override
    public List<Usuario> listarUsuariosMortales() throws Exception{

        return usuarioRepo.obtenerUsuariosMortales();
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
    public List<Producto> obtenerFavoritos(Integer codigo) throws  Exception{
        return usuarioRepo.obtenerProductosFavoritos(codigo);
    }
    @Override
    public void agregarProductoFavorito(Producto producto, Usuario usuario) throws Exception{

        System.out.println("Servicio 1");
        usuario.getListaFavoritos().add(producto);
        System.out.println("Servicio 2");
        usuarioRepo.save(usuario);
        System.out.println("Servicio 3");
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
    public  Usuario recuperarContrasenia(String email, Integer codigo) throws  Exception{

        return usuarioRepo.findByEmailAndCodigo(email, codigo).orElseThrow( () -> new Exception("El usuario no existe"));
    }

    @Override
    public List<Chat> obtenerChatsUsuario(Integer codigo) throws Exception {
        return usuarioRepo.obtenerChatsUsuario(codigo);
    }

    public void agregarFavorito(Producto productoFavorito){

    }

}
