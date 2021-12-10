package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Oferta;
import co.edu.uniquindio.unishop.entidades.Subasta;

import java.util.List;

public interface SubastaServicio {

    List<Subasta>listarSubastas();

    Subasta obtenerSubastaCodigo(Integer codigo);

    void actualizarSubasta(Subasta subasta);

    void guardarOferta(Oferta oferta);
}
