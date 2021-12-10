package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Oferta;
import co.edu.uniquindio.unishop.entidades.Subasta;
import co.edu.uniquindio.unishop.entidades.Usuario;
import co.edu.uniquindio.unishop.repositorios.OfertaRepo;
import co.edu.uniquindio.unishop.repositorios.SubastaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubastaServicioImpl implements SubastaServicio{

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private OfertaRepo ofertaRepo;

    @Override
    public List<Subasta>listarSubastas(){
        return subastaRepo.findAll();
    }

    @Override
    public Subasta obtenerSubastaCodigo(Integer codigo){
        return subastaRepo.obtenerSubastaCodigo(codigo);
    }

    @Override
    public void actualizarSubasta(Subasta subasta){
        Optional<Subasta> buscado = subastaRepo.findById((subasta.getCodigo()));
        if(buscado .get().getCodigo()==subasta.getCodigo()){
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            subastaRepo.save(subasta);
        }
    }

    @Override
    public void guardarOferta(Oferta oferta){
        ofertaRepo.save(oferta);
    }
}
