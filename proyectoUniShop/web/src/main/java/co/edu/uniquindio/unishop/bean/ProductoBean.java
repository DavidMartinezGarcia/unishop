package co.edu.uniquindio.unishop.bean;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

@ViewScoped
@Component
public class ProductoBean implements Serializable {
    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private ArrayList<String> imagenes;

    @PostConstruct
    public void inicializar(){

        producto = new Producto();
        this.imagenes = new ArrayList<>();
    }
    @Autowired
    private ProductoServicio productoServicio;

    @Value("${upload.url}")
    private String urlUploads;


    public void crearProducto(){
        try {
            if(!imagenes.isEmpty()) {
                producto.setImagenes(imagenes);
                productoServicio.publicarProducto(producto);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto creado con exito");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else{
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Es necesario subir al menos una imagen");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void subirImagenes(FileUploadEvent event){

        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);

        if(nombreImagen!=null){
            imagenes.add(nombreImagen);
        }
    }
    
    public String subirImagen(UploadedFile imagen){

        try{
            File archivo = new File(urlUploads+"/"+imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
