package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Column(nullable = false)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer unidadesDisponibles;

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
    @Min(0)
    @Max(100)
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

    public Producto(String nombre, String descripcion, Double precio, Integer unidadesDisponibles, Ciudad ciudad, Integer descuento, Date fechaLimite, Usuario vendedor){

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadesDisponibles = unidadesDisponibles;
        this.ubicacion = ciudad;
        this.descuento = descuento;
        this.fechaLimite = fechaLimite;
        this.vendedor = vendedor;

    }
}
