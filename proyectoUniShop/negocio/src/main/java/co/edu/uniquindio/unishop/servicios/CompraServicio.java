package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.Producto;

import java.util.List;

public interface CompraServicio {

    List<Compra> listarComprasUsuario(Integer codigoUsuario) throws Exception;

    List<Producto> listarProductosCompra(Integer codigoCompra) throws Exception;
}
