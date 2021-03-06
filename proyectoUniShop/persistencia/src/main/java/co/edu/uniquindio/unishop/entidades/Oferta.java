package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Oferta implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Subasta subasta;

    @Positive
    @Column(nullable = false)
    private double valor;

    public Oferta(double valor){
        this.valor = valor;
    }
}
