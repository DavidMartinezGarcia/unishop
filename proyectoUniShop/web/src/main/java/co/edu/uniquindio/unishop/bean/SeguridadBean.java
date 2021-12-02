package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.dto.ProductoCarrito;
import co.edu.uniquindio.unishop.entidades.MetodoPago;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

@Component
@Scope("session")
public class SeguridadBean implements Serializable{


    @Getter
    @Setter
    private boolean autenticado;

    @Getter
    @Setter
    private String email, password;

    @Getter
    @Setter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private Double subtotal;

    @PostConstruct
    public void inicializar(){
        this.subtotal = 0D;
        this.productosCarrito = new ArrayList<>();
    }
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String closeSesion(){
        System.out.println("Hola");
        return null;
    }
    public String iniciarSesion() {
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                usuarioSesion = usuarioServicio.iniciarSesion(email, password);
                autenticado = true;
                return "index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }
        return null;
    }

    public void agregarAlCarrito(Integer id, Double precio, String nombre, String imagen){
        ProductoCarrito productoCarrito = new ProductoCarrito(id, nombre, imagen, precio, 1);
        if(!productosCarrito.contains(productoCarrito)){
            productosCarrito.add(productoCarrito);
            subtotal += precio;

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El producto ya se encuentra en el carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }

    }

    public void eliminarDelCarrito(int indice){
        subtotal-=productosCarrito.get(indice).getPrecio();
        productosCarrito.remove(indice);
    }


    public void actualizarSubTotal(){
        subtotal = 0D;
        for(ProductoCarrito producto: productosCarrito){
            subtotal += producto.getPrecio()*producto.getUnidades();
        }
    }
    public Integer consultarMax(Integer codigo) throws Exception{

        Producto p = productoServicio.obtenerProducto(codigo);
        Integer cantidadDispnible = p.getUnidadesDisponibles();

        return cantidadDispnible;

    }
    public void comprar(){

        if(usuarioSesion!=null && !productosCarrito.isEmpty()){
            try{
                //El metodo de pago esta quemado y toca implementarlo !!
                productoServicio.comprarProductos(usuarioSesion, productosCarrito, MetodoPago.NEQUI);
                productosCarrito.clear();
                subtotal = 0D;
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada con exito");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);

            }catch(Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El producto ya se encuentra en el carrito");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }

        }

    }

}
