package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    @ElementCollection()
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
    private LocalDate fechaLimite;

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

    @ManyToMany
    @ToString.Exclude
    private List<Categoria> listaCategorias;

    public Producto(String nombre, String descripcion, Double precio, Integer unidadesDisponibles, Ciudad ciudad, Integer descuento, LocalDate fechaLimite, Usuario vendedor, List<Categoria> categorias){

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidadesDisponibles = unidadesDisponibles;
        this.ubicacion = ciudad;
        this.descuento = descuento;
        this.fechaLimite = fechaLimite;
        this.vendedor = vendedor;
        this.listaCategorias = categorias;

    }

   public String getImagenPrincipal(){

        if(imagenes != null && !imagenes.isEmpty()){
            return imagenes.get(0);
        }
        return "default.png";

   }

    public String mostrarCategorias(){
        String resultado = "";
        for (int i = 0;i<listaCategorias.size();i++) {
            resultado += listaCategorias.get(i).getNombre();
            if(i<listaCategorias.size()-1){
                resultado+=", ";
            }
        }
        return resultado;
    }
}
