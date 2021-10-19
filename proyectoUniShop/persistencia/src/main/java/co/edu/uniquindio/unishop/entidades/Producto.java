package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {

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
    private double precio;

    @Positive
    @Column(nullable = false)
    private int disponibilidad;

    @Future
    @Column(nullable = false)
    private Date fechaLimite;

    @JoinColumn(nullable = false)
    private Ciudad ubicacion;

    @ToString.Exclude
    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentarios;

    @Positive
    @Column(nullable = false)
    private Integer descuento;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @OneToOne
    private Subasta subasta;

    @ManyToMany
    private List<Usuario> listaUsuarios;
    //@ManyToMany
    //@ToString.Exclude
    //private List<Categoria> categorias;
}
