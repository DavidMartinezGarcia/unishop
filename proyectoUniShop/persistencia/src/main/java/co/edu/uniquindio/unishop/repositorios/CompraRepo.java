package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    /**
     * Obtiene la lista de productos comprados por un usuario dado el código
     * @param codigo
     * @return
     */
    @Query("select d.producto from Compra c join c.detalleCompras d where c.usuario.codigo = :codigo")
    List<Producto> obtenerListaProductosComprados(String codigo);

    @Query("select d.producto from Compra c join c.detalleCompras d where c.codigo = :codigoCompra ")
    List<Producto>obtenerProductosCompra(Integer codigoCompra);

    @Query("select sum(d.cantidad*d.producto.precio) from DetalleCompra d where d.producto.vendedor.codigo = :codigo")
    Long calcularTotalVentas(String codigo);

}