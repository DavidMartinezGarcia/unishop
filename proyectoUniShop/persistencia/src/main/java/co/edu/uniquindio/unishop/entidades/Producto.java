package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ElementCollection
    @ToString.Exclude
    private List<String> imagenes;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = true)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Positive
    @Column(nullable = false)
    private Integer disponibilidad;

    @Future
    @Column(nullable = false)
    private Date fechaLimite;

    @ManyToOne
    private Ciudad ubicacion;

    @ManyToOne
    private Usuario vendedor;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @Column(nullable = false)
    private Integer descuento;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @OneToOne
    private Subasta subasta;

    @ManyToMany(mappedBy = "listaFavoritos")
    @ToString.Exclude
    private List<Usuario> listaUsuarios;

    @ManyToMany(mappedBy = "listaProductos")
    @ToString.Exclude
    private List<Categoria> listaCategorias;

    public Producto(String nombre, String descripcion, Double precio, Integer disponibilidad, Ciudad ciudad, Integer descuento, Date fechaLimite, Usuario vendedor){

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.ubicacion = ciudad;
        this.descuento = descuento;
        this.fechaLimite = fechaLimite;
        this.vendedor = vendedor;

    }
}
