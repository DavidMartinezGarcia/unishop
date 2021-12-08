package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.MetodoPago;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.repositorios.CompraRepo;
import co.edu.uniquindio.unishop.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicioImpl implements CompraServicio {

    @Autowired
    private DetalleCompraRepo detalleRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;


    @Override
    public List<Compra> listarComprasUsuario(Integer codigoUsuario) throws Exception{
        return usuarioRepo.obtenerCompras(codigoUsuario);
    }
    @Override
    public List<Producto> listarProductosCompra(Integer codigoCompra) throws Exception{
      return compraRepo.obtenerProductosCompra(codigoCompra);
    }

    @Override
    public MetodoPago obtenerMetodoPago(String nombre) throws Exception {
        return MetodoPago.valueOf(nombre);
    }

    @Override
    public Compra obtenerCompraId(Integer id) throws Exception {
        return compraRepo.getById(id);
    }


}
