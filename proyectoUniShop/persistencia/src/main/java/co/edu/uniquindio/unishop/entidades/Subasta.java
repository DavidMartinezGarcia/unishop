package co.edu.uniquindio.unishop.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subasta implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    private Producto producto;

    @Column(nullable = false)
    @Positive
    private Integer tiempoLimite;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    @JsonIgnore
    private List<Oferta> listaPujas;

    public Subasta(Producto producto, Integer tiempoLimite) {
        this.producto = producto;
        this.tiempoLimite = tiempoLimite;
    }
}
