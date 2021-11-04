package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ciudad {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCiudad;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "ubicacion")
    @ToString.Exclude
    private List<Producto> productos;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
