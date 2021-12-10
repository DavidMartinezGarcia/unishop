package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Categoria;
import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.servicios.CategoriaServicio;
import co.edu.uniquindio.unishop.servicios.CiudadServicio;
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
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    private Categoria categoriaBuscada;

    @Getter @Setter
    private Ciudad ciudadBuscada;

    @Getter @Setter
    private Double precioBuscado;

    @Getter @Setter
    private Integer calificacionBuscada;

    @Getter @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    @Value("#{param['categoria']}")
    private String categoriaParam;

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<Ciudad> listaCiudades;

    @Getter @Setter
    private List<Categoria> listaCategorias;

    @Getter @Setter
    private List<Integer> listaCalificaciones;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @PostConstruct
    public void inicializar(){

        listaCiudades = ciudadServicio.listarCiudades();
        listaCategorias = categoriaServicio.listarCategorias();
        llenarCalificaciones();

        if(busquedaParam!=null && !busquedaParam.isEmpty()){
                productos = productoServicio.buscarProductos(busquedaParam,null);
        }
        if(categoriaParam!=null && !categoriaParam.isEmpty()){
            Integer codigo = Integer.parseInt(categoriaParam);
            Categoria categoria = categoriaServicio.obtenerCategoria(codigo);
            productos = productoServicio.listarProductosCategoria(categoria);
        }
    }

    public void llenarCalificaciones(){

        listaCalificaciones = new ArrayList<Integer>();
        Integer num = 0;
            for(int i=0; i<6; i++){

                listaCalificaciones.add(num);
                num++;

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
        this.categoriaBuscada = categoria;
        if(!categoriaBuscada.equals(null)){
            return "resultado_busqueda?faces-redirect=true&amp;categoria="+categoriaBuscada.getCodigoCategoria();
        }
        else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingresa algo para buscar");
            FacesContext.getCurrentInstance().addMessage("search-msj", msg);
        }
        return null;
    }

    public String buscarFiltros(){

        try{
            productos = productoServicio.listarProductosFiltros(busquedaParam, categoriaBuscada,ciudadBuscada, precioBuscado, calificacionBuscada);
            //Falta que actualice en la vista

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
