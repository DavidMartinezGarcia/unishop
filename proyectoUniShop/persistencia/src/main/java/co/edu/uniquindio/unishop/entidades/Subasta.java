package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subasta {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @OneToOne(mappedBy = "subasta")
    private Producto producto;

    @Column(nullable = false)
    @Positive
    private Integer tiempoLimite;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    private List<Oferta> listaPujas;
}
