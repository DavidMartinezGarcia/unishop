package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Categoria;

import java.util.List;

public interface CategoriaServicio {

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(Integer id);
}
