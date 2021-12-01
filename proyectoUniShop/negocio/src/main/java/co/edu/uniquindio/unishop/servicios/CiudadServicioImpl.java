package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Ciudad;
import co.edu.uniquindio.unishop.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServicioImpl implements CiudadServicio{

    @Autowired
    private CiudadRepo ciudadRepo;

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Ciudad obtenerCiudad(Integer id) throws Exception{

        return ciudadRepo.findById(id).orElseThrow( () -> new Exception("El id no corresponde a ninguna ciudad"));
    }
}
