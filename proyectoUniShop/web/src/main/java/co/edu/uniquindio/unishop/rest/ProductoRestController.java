package co.edu.uniquindio.unishop.rest;

import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listar(){

        try {
            return productoServicio.listarTodosLosProductos();
        }catch(Exception e){

        }
       return null;
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable("id") Integer codigoProducto) throws Exception{
        return productoServicio.obtenerProducto(codigoProducto);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) throws Exception{
        return productoServicio.publicarProducto(producto);
    }

    @PutMapping
    public void actualizar(@RequestBody Producto producto) throws Exception{
        productoServicio.actualizarProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer codigoProducto) throws Exception{
        productoServicio.eliminarProducto(codigoProducto);
    }

    /*@GetMapping("/{id}")
    public Producto obtenerPorCategoria(@PathVariable("id") Integer codigoProducto) throws Exception{
        //return productoServicio.listarProductosCategoria();
        return null;
    }*/
}
