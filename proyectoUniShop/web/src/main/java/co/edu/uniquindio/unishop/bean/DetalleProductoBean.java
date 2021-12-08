package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Comentario;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import co.edu.uniquindio.unishop.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Component
@ViewScoped
@Transactional
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private Integer calificacionPromedio;

    @Getter @Setter
    private Integer descuento;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Getter @Setter
    private String respuesta;

    @Value(value = "#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Value(value = "#{usuarioBean.productosFavoritos}")
    private List<Producto> productosFavoritos;

    @PostConstruct
    public void inicializar() {

        nuevoComentario = new Comentario();
        if (codigoProducto != null && !codigoProducto.isEmpty()) {
            Integer codigo = Integer.parseInt(codigoProducto);
            try {
                producto = productoServicio.obtenerProducto(codigo);
                this.comentarios = producto.getComentarios();
                mostrarCalificacionPromedio();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void mostrarCalificacionPromedio() {
        int promedio = 0;
        int contador = 0;
        if(producto.getComentarios() != null && producto.getComentarios().size() !=0) {
            for(Comentario comentario:producto.getComentarios()){
                promedio += comentario.getPuntuacion();
                contador++;
            }
            this.calificacionPromedio = promedio/contador;
        }

    }

    public void crearComentario() {
        try {
            if(usuarioSesion!=null) {
                nuevoComentario.setProducto(producto);
                nuevoComentario.setUsuario(usuarioSesion);
                nuevoComentario.setFecha(LocalDate.now());
                productoServicio.comentarProducto(nuevoComentario);

                this.comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();
            }else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Debes ingresar a tu cuenta para comentar");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void agregarFavoritos(){
        try{
            System.out.println("Nombre usuario: "+usuarioSesion.getNombre());
            System.out.println("Lista favoritos: "+usuarioSesion.getListaFavoritos());
            usuarioSesion.getListaFavoritos().add(producto);
            usuarioServicio.actualizarUsuario(usuarioSesion);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void abrirResponder(Comentario comentario){
        if(comentario.getRespuesta()==null){
            comentario.setRespuesta(":");
        }
    }

    public void darRespuesta(Comentario comentario){
        if(respuesta.trim().equals("")){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede crear una respuesta vacia");
            FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
        }else {
            comentario.setRespuesta(respuesta.trim());
            try {
                productoServicio.comentarProducto(comentario);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }
    }

    public boolean isInFavoritos(){
        boolean centinela = false;
        if(usuarioSesion.getListaFavoritos().contains(producto)){
            centinela = true;
        }
        return centinela;
    }

    public boolean isntInFavoritos(){
        boolean centinela = true;
        if(usuarioSesion.getListaFavoritos().contains(producto)){
            centinela = false;
        }
        return centinela;
    }

    public void eliminarFavorito(){
        try{
            productosFavoritos.remove(producto);
            usuarioSesion.getListaFavoritos().remove(producto);
            usuarioServicio.actualizarUsuario(usuarioSesion);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

