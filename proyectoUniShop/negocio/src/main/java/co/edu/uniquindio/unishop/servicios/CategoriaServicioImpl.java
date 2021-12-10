package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Categoria;
import co.edu.uniquindio.unishop.repositorios.CategoriaRepo;
import co.edu.uniquindio.unishop.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class CategoriaServicioImpl implements CategoriaServicio, Serializable {

    @Autowired
    private CategoriaRepo categoriaRepo;


    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Integer id) {
        return categoriaRepo.findById(id).orElse(null);
    }

}
