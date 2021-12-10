package co.edu.uniquindio.unishop.servicios;


import co.edu.uniquindio.unishop.dto.ProductoCarrito;
import co.edu.uniquindio.unishop.entidades.*;
import co.edu.uniquindio.unishop.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private SubastaRepo subastaRepo;


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
        try {
            productoRepo.save(producto);

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);
        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }else{
            productoRepo.deleteById(codigo);
        }
    }

    @Override
    public void borrarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);
        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }else{
            productoRepo.borrarProducto(codigo);
        }
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(() -> new Exception("El código del producto no es válido"));
    }

    @Override
    public List<Producto> listarProductosCategoria(Categoria categoria) {
        return productoRepo.buscarProductosCategoria(categoria);
    }

    @Override
    public List<Producto> listarTodosLosProductos() throws Exception {
        return productoRepo.findAll();
    }
    @Override
    public List<Producto> listarProductosFiltros(String busquedaParam,Categoria categoria, Ciudad ciudad, Double precio, Integer calificacion) throws Exception{

        List<Producto> productosFiltrados = new ArrayList<>();
        List<Producto> productosFiltradosNombre = new ArrayList<>();
        List<Producto> productosFiltradosCategoria = new ArrayList<>();
        List<Producto> productosFiltradosCiudad = new ArrayList<>();
        List<Producto> productosFiltradosPrecio = new ArrayList<>();
        List<Producto> productosFiltradosCalificacion = new ArrayList<>();
        boolean flag = false;

        productosFiltradosNombre = productoRepo.buscarProductoNombre(busquedaParam);

        if(categoria != null){
            productosFiltradosCategoria = productoRepo.buscarProductosCategoria(categoria);
        }
        if(ciudad != null){
            productosFiltradosCiudad = productoRepo.buscarProductosUbicacion(busquedaParam,ciudad);
        }
        if(precio != null){
            productosFiltradosPrecio =productoRepo.buscarProductosPrecio(busquedaParam,precio);
        }
        if(calificacion != null){
            productosFiltradosCalificacion = productoRepo.buscarProductosCalificacion(busquedaParam,calificacion);
        }

        for(int i=0; i<productosFiltradosNombre.size();i++){

            Producto p = productosFiltrados.get(i);

            if(productosFiltradosCategoria.contains(p)){
                flag = flag && true;
            }
            if(productosFiltradosCiudad.contains(p)){
                flag = flag && true;
            }
            if(productosFiltradosPrecio.contains(p)){
                flag = flag && true;
            }
            if(productosFiltradosCalificacion.contains(p) ){
                flag = flag && true;
            }

        }

        return productosFiltrados;
    }
    @Override
    public List<Producto> listarProductosSubastados() throws Exception{
        return productoRepo.buscarProductosSubastados();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentarioRepo.save(comentario);
    }
    @Override
    public void subastarProducto(Subasta subasta) throws Exception {
        subastaRepo.save(subasta);
    }

    @Override
    public void agregarProductoFavorito(Producto producto, Usuario usuario) throws Exception{

        Optional<Producto> buscado = productoRepo.findById((producto.getCodigo())); //Revisar

        producto.getListaUsuarios().add(usuario);

        if(producto.getListaUsuarios() == null){
            System.out.println("Es nula :c");
        }else{
            System.out.println("WTF?");
        }

        productoRepo.save(producto);

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
    public List<Producto> listarProductosVendedor(Integer codigoUsuario) throws Exception {
        return productoRepo.buscarProductosVendedor(codigoUsuario);
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
                Producto ptemp =productoRepo.findById(p.getId()).get();
                dc = new DetalleCompra();
                dc.setCompra(compraGuardada);
                dc.setCantidad(p.getUnidades());
                dc.setProducto(ptemp);

                detalleCompraRepo.save(dc);
                ptemp.setUnidadesDisponibles(ptemp.getUnidadesDisponibles()-p.getUnidades());
                productoRepo.save(ptemp);
            }



            return compraGuardada;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public List<Producto> buscarProductosDescuento() throws Exception{

        return productoRepo.buscarProductosDescuento();
    }

    @Override
    public List<String> listarMetodosPago(){
        MetodoPago[] metodos = MetodoPago.values();
        List<String> listaLlena = new ArrayList<>();
        for (MetodoPago metodoTemp:metodos) {
            listaLlena.add(metodoTemp.name());
        }
        return listaLlena;
    }

    @Override
    public MetodoPago obtenerMetodoPago(String metodoPago) throws Exception {
        return MetodoPago.valueOf(metodoPago);
    }

    public void agregarFavorito(Producto productoFavorito){

    }
}
