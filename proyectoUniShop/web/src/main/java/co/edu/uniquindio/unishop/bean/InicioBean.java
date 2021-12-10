package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.servicios.CategoriaServicio;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped

public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Getter @Setter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar(){
        try {
            this.productos = productoServicio.listarTodosLosProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String irADetalle(String id){
        return "/detalle_producto?faces-redirect=true&amp;producto="+id;
    }

}
