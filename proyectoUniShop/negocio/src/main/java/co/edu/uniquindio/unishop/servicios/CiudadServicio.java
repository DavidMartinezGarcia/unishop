package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Ciudad;

import java.util.List;

public interface CiudadServicio {

    List<Ciudad> listarCiudades();

    Ciudad obtenerCiudad(Integer id) throws Exception;


}
