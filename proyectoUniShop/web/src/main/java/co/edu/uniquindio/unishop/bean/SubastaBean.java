package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.dto.SubastaProducto;
import co.edu.uniquindio.unishop.entidades.Oferta;
import co.edu.uniquindio.unishop.entidades.Subasta;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import co.edu.uniquindio.unishop.servicios.SubastaServicio;
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

@ViewScoped
@Component
public class SubastaBean implements Serializable {

    @Getter @Setter
    private List<SubastaProducto> productosSubastados;

    @Value(value = "#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private String valorOferta;

    @Getter @Setter
    private Oferta ofertaActual;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private SubastaProducto subastaProducto;

    @Autowired
    private SubastaServicio subastaServicio;

    @PostConstruct
    public void inicializar(){
        productosSubastados = new ArrayList<>();
    }

    public void agregarProductosSubastados(){
        try{
            List<Subasta> subastas = subastaServicio.listarSubastas();
            for (Subasta s: subastas) {
                SubastaProducto subastaProducto = new SubastaProducto(s.getCodigo(), s.getProducto().getImagenPrincipal(), s.getProducto().getNombre(), s.getTiempoLimite(), s.obtenerOfertaMayor(), s.getListaPujas().size());
                if(!productosSubastados.contains(subastaProducto)){
                    productosSubastados.add(subastaProducto);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

    public String crearOferta(){
        Subasta subasta = subastaServicio.obtenerSubastaCodigo(subastaProducto.getId());
        Oferta oferta = new Oferta();
        oferta.setValor(Double.parseDouble(valorOferta));
        oferta.setSubasta(subasta);
        subasta.getListaPujas().add(oferta);
        subastaServicio.guardarOferta(oferta);
        subastaServicio.actualizarSubasta(subasta);
        return "/usuario/subastas?faces-redirect=true";
    }

    public void ponerSubasta(SubastaProducto subastaProducto){
        this.subastaProducto = subastaProducto;
    }
}
