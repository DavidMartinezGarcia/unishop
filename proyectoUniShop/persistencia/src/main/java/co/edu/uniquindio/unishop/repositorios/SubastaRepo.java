package co.edu.uniquindio.unishop.repositorios;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubastaRepo extends JpaRepository<Subasta, Integer> {

    //Consulta que devuelve los productos de cada subasta
    @Query("select s.producto from Subasta s where s.producto is not null")
    List<Producto> listarProductosSubastados();

    @Query("select s from Subasta s where s.codigo =:codigo")
    Subasta obtenerSubastaCodigo(Integer codigo);

}
