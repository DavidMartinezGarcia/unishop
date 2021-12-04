package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped

public class DescuentoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Getter
    @Setter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar(){
        try{
            productos = productoServicio.buscarProductosDescuento();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
