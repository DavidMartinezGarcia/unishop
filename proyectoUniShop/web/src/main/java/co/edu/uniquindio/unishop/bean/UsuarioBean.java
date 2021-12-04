package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.dto.ProductoUsuario;
import co.edu.uniquindio.unishop.dto.UsuarioCompra;
import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.CiudadServicio;
import co.edu.uniquindio.unishop.servicios.CompraServicio;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Component

public class UsuarioBean implements Serializable {

    @Getter @Setter
    private Usuario usuario;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private String telefono1;

    @Getter @Setter
    private String telefono2;

    @Getter @Setter
    private List<Ciudad> listaCiudades;

    @Getter @Setter
    private List<ProductoUsuario> productosUsuario;

    @Getter @Setter
    private List<ProductoUsuario> productosFavoritos;

    @Autowired
    private CompraServicio compraServicio;

    @Getter @Setter
    private List<UsuarioCompra> listaCompras;

    @PostConstruct
    public void inicializar(){
        usuario = new Usuario();
        listaCiudades = ciudadServicio.listarCiudades();
        productosUsuario = new ArrayList<ProductoUsuario>();
        productosFavoritos = new ArrayList<ProductoUsuario>();
        listaCompras = new ArrayList<UsuarioCompra>();
    }

    public void registrarUsuario(){
        try{
            ArrayList<String> telefonos = new ArrayList<>();
            if(!telefono1.trim().equals("")){
                telefonos.add(telefono1);
            }
            if(!telefono2.trim().equals("")){
                telefonos.add(telefono2);
            }
            usuario.setTelefonos(telefonos);
            usuarioServicio.registrarUsuario(usuario);

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void agregarProductosUsuario(Integer codigo){
        try {
            List<Producto> productosVendedor = productoServicio.listarProductosVendedor(codigo);
            for (Producto producto: productosVendedor) {
                ProductoUsuario productoUsuario = new ProductoUsuario(producto.getCodigo(),producto.getNombre(),producto.getImagenPrincipal(),producto.getPrecio(),producto.getUnidadesDisponibles());
                if(!productosUsuario.contains(productoUsuario)){
                    productosUsuario.add(productoUsuario);
                }
            }
        }catch(Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }

    public void agregarCompraUsuario(Integer codigoUsuario){

       try {
            List<Compra> listasCompra = compraServicio.listarComprasUsuario(codigoUsuario);
            for(Compra compra: listasCompra){
                UsuarioCompra compraUsuario =  new UsuarioCompra(compra.getMetodoDePago(), compra.getFecha());

                if(!listaCompras.contains(compraUsuario)){
                    listaCompras.add(compraUsuario);
                }
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

    public void agregarProductoCompra(){


    }

    public void agregarProductosFavoritos(Integer codigo){

        try{
            List<Producto> productosVendedor = usuarioServicio.obtenerFavoritos(codigo);
            for (Producto producto: productosVendedor) {
                ProductoUsuario productoUsuario = new ProductoUsuario(producto.getCodigo(),producto.getNombre(),producto.getImagenPrincipal(),producto.getPrecio(),producto.getUnidadesDisponibles());
                if(!productosFavoritos.contains(productoUsuario)){
                    productosFavoritos.add(productoUsuario);
                }
            }


        }catch(Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }


    }
}
