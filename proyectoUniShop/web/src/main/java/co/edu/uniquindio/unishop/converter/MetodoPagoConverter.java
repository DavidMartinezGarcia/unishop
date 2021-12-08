package co.edu.uniquindio.unishop.converter;

import co.edu.uniquindio.unishop.entidades.MetodoPago;
import co.edu.uniquindio.unishop.servicios.CompraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class MetodoPagoConverter implements Converter<MetodoPago>, Serializable {

    @Autowired
    private CompraServicio compraServicio;

    @Override
    public MetodoPago getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        MetodoPago metodo = null;
        try {
            metodo = compraServicio.obtenerMetodoPago(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metodo;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, MetodoPago metodoPago) {
        if(metodoPago!=null){
            return metodoPago.name();
        }
        return "";
    }
}
