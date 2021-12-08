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
public class TipoUsuario {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoTipoUsuario;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "tipoUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> usuarios;

    public TipoUsuario(String nombre) {
        this.nombre = nombre;
    }
}
