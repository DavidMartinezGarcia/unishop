package co.edu.uniquindio.unishop.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer codigo;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> detalleCompras;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoDePago;

    public Compra(MetodoPago metodoDePago,LocalDate fecha){
        this.metodoDePago = metodoDePago;
        this.fecha = fecha;
    }
    public Compra(MetodoPago metodoDePago,LocalDate fecha, ArrayList<DetalleCompra> detalleCompras) {
        this.metodoDePago = metodoDePago;
        this.fecha = fecha;
        this.detalleCompras = detalleCompras;
    }
}
