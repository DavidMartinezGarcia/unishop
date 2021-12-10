package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CategoriaServicio {

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(Integer id);

}
