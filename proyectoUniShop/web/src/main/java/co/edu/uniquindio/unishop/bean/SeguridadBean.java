package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.dto.ProductoCarrito;
import co.edu.uniquindio.unishop.dto.ProductoUsuario;
import co.edu.uniquindio.unishop.entidades.*;
import co.edu.uniquindio.unishop.servicios.CategoriaServicio;
import co.edu.uniquindio.unishop.servicios.MailService;
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
import java.util.List;

@Component
@Scope("session")
public class SeguridadBean implements Serializable{

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private List<String> metodosDePago;

    @Getter @Setter
    private Double subtotal;

    @Getter @Setter
    private String metodoSeleccionado;

    @Getter @Setter
    List<Categoria> categorias;

    @Autowired
    private MailService correoService;

    @PostConstruct
    public void inicializar(){
        this.subtotal = 0D;
        this.productosCarrito = new ArrayList<>();
        metodosDePago = productoServicio.listarMetodosPago();
        this.categorias = categoriaServicio.listarCategorias();
    }
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
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
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe llenar los datos requeridos");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }
        return null;
    }

    public void agregarAlCarrito(Integer id, Double precio, String nombre, String imagen) throws Exception{
        Producto productoTemp = productoServicio.obtenerProducto(id);
        Double precioDescuento = precio - (precio*productoTemp.getDescuento()/100);
        ProductoCarrito productoCarrito = new ProductoCarrito(id, nombre, imagen, precioDescuento, 1);
        if(productoTemp.getUnidadesDisponibles()!=0) {
            if (!productosCarrito.contains(productoCarrito)) {
                productosCarrito.add(productoCarrito);
                subtotal += precioDescuento;
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
                FacesContext.getCurrentInstance().addMessage("add-cart", fm);
            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El producto ya se encuentra en el carrito");
                FacesContext.getCurrentInstance().addMessage("add-cart", fm);
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe disponibilidad de este producto");
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
                System.out.println("METODO DE MIERDA: "+metodoSeleccionado);
                //MetodoPago.valueOf(MetodoPago.class ,metodoSeleccionado)
                productoServicio.comprarProductos(usuarioSesion, productosCarrito, MetodoPago.OTRO);
                enviarEmailProductos();
                productosCarrito.clear();
                subtotal = 0D;

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada con exito");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);

            }catch(Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El producto ya se encuentra en el carrito");
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Error al realizar la compra");
            FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
        }

    }
    public void enviarEmailProductos(){

        try{
            String detalleCompra = "¡Hola, "+usuarioSesion.getNombre() +
            "UniShop te informa: \nAcabas de comprar los siguientes artículos en nuestra tienda:\n";
            String email = usuarioSesion.getEmail();

            for (ProductoCarrito producto: productosCarrito) {

                detalleCompra += "Nombre Producto: "+producto.getNombre()+
                        "\n\tPrecio: "+producto.getPrecio()*producto.getUnidades()+" \n";
            }
            detalleCompra += "\nEl valor total de tu compra fue: "+subtotal;
            detalleCompra += "¡Disfruta de tus productos!";
            correoService.sendSimpleMail(new Mail("UwU@gmail.com", email, "Detalle de tu compra", detalleCompra, null, null));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Mail enviado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Mail no enviado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }


    }
    public boolean descontarProducto(Double descuento){

        boolean descontado= false;

        if(descuento>0){
            descontado = true;
        }
        return descontado;
    }

    public String irARecuperar(){

        return "/recuperar_contrasenia?faces-redirect=true";
    }

    public boolean puedeResponder(Producto producto){
        boolean centinela = false;
        if(producto.getVendedor().equals(usuarioSesion)){
            centinela = true;
        }
        return centinela;
    }



}
