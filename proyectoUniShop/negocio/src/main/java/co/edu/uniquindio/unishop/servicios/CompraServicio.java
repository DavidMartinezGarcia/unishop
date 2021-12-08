package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.MetodoPago;
import co.edu.uniquindio.unishop.entidades.Producto;

import java.util.List;

public interface CompraServicio {

    List<Compra> listarComprasUsuario(Integer codigoUsuario) throws Exception;

    List<Producto> listarProductosCompra(Integer codigoCompra) throws Exception;

    MetodoPago obtenerMetodoPago(String nombre) throws Exception;

    Compra obtenerCompraId(Integer id) throws Exception;
}
