package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    Page<Producto> findAll(Pageable paginador);

    //metodo para navegar a travez de los objetos
    @Query("select p.vendedor.nombre from Producto p where p.codigo = :codigo")
    String obtenerNombreVendedor(Integer codigo);
}
