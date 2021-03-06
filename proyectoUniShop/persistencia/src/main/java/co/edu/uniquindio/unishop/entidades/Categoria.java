package co.edu.uniquindio.unishop.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCategoria;

    @Column(nullable = false)
    private String nombre;


    @ToString.Exclude
    @ManyToMany(mappedBy = "listaCategorias")
    @JsonIgnore
    private List<Producto> listaProductos;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}
