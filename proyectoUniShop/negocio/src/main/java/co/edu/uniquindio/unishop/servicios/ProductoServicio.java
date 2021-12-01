package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.*;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    List<Producto> listarTodosLosProductos() throws Exception;

    void comentarProducto(Comentario comentario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    Compra comprarProductos(Compra compra) throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductos(Integer codigoUsuario) throws Exception;


}
