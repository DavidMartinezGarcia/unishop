package co.edu.uniquindio.unishop.servicios;

import co.edu.uniquindio.unishop.entidades.Compra;
import co.edu.uniquindio.unishop.entidades.DetalleCompra;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.repositorios.CompraRepo;
import co.edu.uniquindio.unishop.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.unishop.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class DetalleProductoServicioImpl implements DetalleProductoServicio{

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

}
