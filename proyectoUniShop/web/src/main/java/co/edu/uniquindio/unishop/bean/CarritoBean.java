package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.DetalleCompra;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class CarritoBean {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private double total;

    //Creacion del detalle producto
    @Getter @Setter
    private DetalleCompra nuevoDetalleCompra;
    @Getter @Setter
    private Producto nuevoProducto;
    @Getter @Setter
    private int nuevoCantidad;

    @Getter @Setter
    private List<DetalleCompra> productosCarrito;


    @PostConstruct
    public void inicializar(){
        nuevoCantidad = 1;
    }
    public void crearCompra(){


        DetalleCompra detalle = new DetalleCompra();
        Compra compra = new Compra();

        try {
            productoServicio.comprarProductos(compra);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcularTotal(){
        double total = 0;
        for(DetalleCompra detalle: productosCarrito){
            total = detalle.getProducto().getPrecio()*detalle.getCantidad();
        }
        this.total = total;
    }

    public void crearDetalle(){
        DetalleCompra nuevoDetalle = new DetalleCompra();
        nuevoDetalle.setProducto(nuevoProducto);
        nuevoDetalle.setCantidad(nuevoCantidad);
        this.productosCarrito.add(nuevoDetalle);
    }


    public void sumarCantidad() {
        this.nuevoCantidad++;
    }
    public void restarCantidad() {
        this.nuevoCantidad--;
    }
}
