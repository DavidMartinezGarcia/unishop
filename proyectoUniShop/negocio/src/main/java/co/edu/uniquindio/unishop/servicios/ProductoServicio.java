package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.dto.ProductoCarrito;
import co.edu.uniquindio.unishop.entidades.*;

import java.util.ArrayList;
import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    void borrarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductosCategoria(Categoria categoria);

    List<Producto> listarTodosLosProductos() throws Exception;

    List<Producto> listarProductosSubastados() throws Exception;

    void comentarProducto(Comentario comentario) throws Exception;

    void subastarProducto(Subasta subasta, Producto producto) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void agregarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    Compra comprarProductos(Compra compra) throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductosVendedor(Integer codigoUsuario) throws Exception;

    Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, MetodoPago metodoPago) throws Exception;

    List<Producto> buscarProductosDescuento() throws Exception;

    List<String> listarMetodosPago();

    MetodoPago obtenerMetodoPago(String metodoPago) throws  Exception;

}
