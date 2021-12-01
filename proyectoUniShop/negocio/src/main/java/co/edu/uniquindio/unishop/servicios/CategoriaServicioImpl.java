package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Categoria;
import co.edu.uniquindio.unishop.repositorios.CategoriaRepo;
import co.edu.uniquindio.unishop.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class CategoriaServicioImpl implements CategoriaServicio, Serializable {

    @Autowired
    private CategoriaRepo categoriaRepo;


    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Integer id) throws Exception {
        return categoriaRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ninguna categoría"));
    }
}
