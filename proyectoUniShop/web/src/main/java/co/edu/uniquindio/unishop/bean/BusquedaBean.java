package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Categoria;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.servicios.CategoriaServicio;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    private Categoria categoriaBuscada;

    @Getter @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    @Value("#{param['categoria']}")
    private String categoriaParam;

    @Getter @Setter
    private List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @PostConstruct
    public void inicializar(){
        if(busquedaParam!=null && !busquedaParam.isEmpty()){
                productos = productoServicio.buscarProductos(busquedaParam,null);
        }
        if(categoriaParam!=null && !categoriaParam.isEmpty()){
            Integer codigo = Integer.parseInt(categoriaParam);
            Categoria categoria = categoriaServicio.obtenerCategoria(codigo);
            productos = productoServicio.listarProductosCategoria(categoria);
        }
    }

    public String buscar(){
        if(!busqueda.trim().equals("")) {
            return "resultado_busqueda?faces-redirect=true&amp;busqueda=" + busqueda;
        }
        else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingresa algo para buscar");
            FacesContext.getCurrentInstance().addMessage("search-msj", msg);
        }
        return null;
    }

    public String buscarCategoria(Categoria categoria){
        System.out.println("Entra");
        this.categoriaBuscada = categoria;
        if(!categoriaBuscada.equals(null)){
            System.out.println("Entra if magico");
            return "resultado_busqueda?faces-redirect=true&amp;categoria="+categoriaBuscada.getCodigoCategoria();
        }
        else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingresa algo para buscar");
            FacesContext.getCurrentInstance().addMessage("search-msj", msg);
        }
        return null;
    }
}
