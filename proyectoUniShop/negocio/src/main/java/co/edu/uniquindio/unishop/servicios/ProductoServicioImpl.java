package co.edu.uniquindio.unishop.servicios;


import co.edu.uniquindio.unishop.dto.ProductoCarrito;
import co.edu.uniquindio.unishop.entidades.*;
import co.edu.uniquindio.unishop.repositorios.ComentarioRepo;
import co.edu.uniquindio.unishop.repositorios.CompraRepo;
import co.edu.uniquindio.unishop.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.unishop.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try {
            Producto producto = productoRepo.save(p);

            return producto;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {


    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);
        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(() -> new Exception("El código del producto no es válido"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return productoRepo.buscarProductosCategoria(categoria);
    }

    @Override
    public List<Producto> listarTodosLosProductos() throws Exception {
        return productoRepo.findAll();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFecha(LocalDate.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public Compra comprarProductos(Compra compra) throws Exception {

        try {
            Compra compraU = compraRepo.save(compra);

            return compraU;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {
        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductos(Integer codigoUsuario) throws Exception {
        return null;
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, MetodoPago metodoPago) {

        try{
            Compra c = new Compra();
            c.setFecha(LocalDate.now(ZoneId.of("America/Bogota")));
            c.setUsuario(usuario);
            c.setMetodoDePago(metodoPago);

            Compra compraGuardada = compraRepo.save(c);

            DetalleCompra dc;

            for(ProductoCarrito p : productos){

                dc = new DetalleCompra();
                dc.setCompra(compraGuardada);
                dc.setCantidad(p.getUnidades());
                dc.setProducto(productoRepo.findById(p.getId()).get());

                detalleCompraRepo.save(dc);
            }
            return compraGuardada;
        }catch (Exception e){

        }
        return null;
    }
}
