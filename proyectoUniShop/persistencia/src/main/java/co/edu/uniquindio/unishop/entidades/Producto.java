package co.edu.uniquindio.unishop.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @JsonIgnore
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
    @JsonIgnore
    private List<Comentario> comentarios;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private Integer descuento;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> detalleCompras;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    @ToString.Exclude
    private List<Subasta> subastas;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "listaFavoritos")
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> listaUsuarios;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Categoria> listaCategorias;

    @Getter @Setter
    private Integer calificacion;

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
        calificacion = 0;
        listaUsuarios = new ArrayList<>();

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
    public Double calcularPrecioDescuento(){
        return precio - (precio*descuento/100);
    }

    public void obtenerCalificacion(){

        Integer sum=0;
        for (Comentario c: comentarios) {
            sum+= c.getPuntuacion();
        }
        calificacion = sum / comentarios.size();
    }
}
