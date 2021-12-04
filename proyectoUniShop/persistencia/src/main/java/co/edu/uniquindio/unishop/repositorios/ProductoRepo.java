package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Categoria;
import co.edu.uniquindio.unishop.entidades.Comentario;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
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


}
