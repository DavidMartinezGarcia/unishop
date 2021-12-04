package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.dto.ProductoCarrito;
import co.edu.uniquindio.unishop.dto.UsuarioCompra;
import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.servicios.CompraServicio;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import co.edu.uniquindio.unishop.entidades.DetalleCompra;
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
public class CompraBean implements Serializable {

    @Getter
    @Setter
    private List<UsuarioCompra> listaCompras;

    @Getter
    @Setter
    private List<ProductoCarrito> listaProductosCompra;

    @Autowired
    private CompraServicio compraServicio;

    @PostConstruct
    public void inicializar(){

            this.listaCompras = new ArrayList<>();
    }

    public void agregarDetalleUsuario(Integer codigoCompra){

        try {
            List<Producto> listaProductosCompra = compraServicio.listarProductosCompra(codigoCompra);
             for(Producto producto: listaProductosCompra){

                    ProductoCarrito productoCompra = new ProductoCarrito(producto.getCodigo(), producto.getNombre(),
                            producto.getImagenPrincipal(), producto.getPrecio(), producto.getUnidadesDisponibles());

             }
        } catch (Exception e) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }
}
