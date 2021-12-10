package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    Page<Producto> findAll(Pageable paginador);

    //metodo para navegar a travez de los objetos
    @Query("select p.vendedor.nombre from Producto p where p.codigo = :codigo")
    String obtenerNombreVendedor(Integer codigo);

    @Query("select c from Producto p join p.comentarios c where p.codigo = :id")
    List<Comentario> obtenerComentarios(Integer id);

    @Query("select p.nombre, c from Producto p left join p.comentarios c")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.usuario from Producto p join p.comentarios c where p.codigo = :id")
    List<Usuario> listarUsuariosComentarios(Integer id);

    //@Query("select new co.edu.uniquindio.unishop.dto.ProductoValido(p.nombre, p.descripcion, p.precio, p.ubicacion) from Producto p where :fechaActual < p.fechaLimite")
    //List<Object[]> listarProductosValidos(LocalDateTime fechaAcual);

    @Query("select c, count(p) from Producto p join p.listaCategorias c group by c")
    List<Object[]> obtenerTotalProductosPorCategoria();

    @Query("select p from Producto p where p.comentarios is empty")
    List<Producto> obtenerProductosSinComentarios();

    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select p from Producto p where :categoria member of p.listaCategorias")
    List<Producto> buscarProductosCategoria(Categoria categoria);

    //Consulta para obtener los productos con descuento
    @Query("select p from Producto p where p.descuento > 0")
    List<Producto> buscarProductosDescuento();

    //Consulta para obtener los productos publicados por un ususario
    @Query("select p from Producto p where p.vendedor.codigo = :codigoUsuario")
    List<Producto> buscarProductosVendedor(Integer codigoUsuario);

    @Query("DELETE from Producto where codigo=:codigoProducto")
    boolean borrarProducto(Integer codigoProducto);

    //Consulta para obtener los productos que están siendo subastados
    @Query("select  p from Producto p where p.subastas is not empty")
    List<Producto> buscarProductosSubastados();

    //Consulta para obtener los productos filtrados por Nombre, Categoria, Ubicación, Precio, Calificación -> En proceso me quedo grande
    @Query("select  p from Producto p " +
            "where p.nombre like concat('%', :nombre, '%') and (" +
            "(:categoria is not null and :categoria member of p.listaCategorias) and " +
            "(:ciudad is not null and :ciudad = p.ubicacion and p.precio = :precio ) and " +
            "(:calificacion is not null and p.calificacion = :calificacion))")
    List<Producto> buscarProductosFiltrados(String nombre, Categoria categoria, Ciudad ciudad, Double precio, Integer calificacion);

    //Consulta para obtener los productos filtrados por su Ubicación
    @Query("select p from Producto p where p.nombre like concat('%', :busquedaParam, '%') and :ciudad = p.ubicacion")
    List<Producto> buscarProductosUbicacion(String busquedaParam,Ciudad ciudad);

    //Consulta para obtener los productos filtrados por su precio
    @Query("select p from Producto p where p.nombre like concat('%', :busquedaParam, '%') and :precio = p.precio")
    List<Producto> buscarProductosPrecio(String busquedaParam,Double precio);

    //Consulta para obtener los productos filtrados por su calificacion
    @Query("select p from Producto p where p.nombre like concat('%', :busquedaParam, '%') and :calificacion = p.calificacion")
    List<Producto> buscarProductosCalificacion(String busquedaParam,Integer calificacion);



}
