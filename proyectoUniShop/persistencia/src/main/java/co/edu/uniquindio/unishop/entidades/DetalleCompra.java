package co.edu.uniquindio.unishop.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleCompra implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Compra compra;

    @Positive
    @Column(nullable = false)
    private Integer cantidad;

    @OneToOne(mappedBy = "codigoProducto")
    @JsonIgnore
    private Chat chat;

    public DetalleCompra(Producto producto, Compra compra, Integer cantidad) {
        this.producto = producto;
        this.compra = compra;
        this.cantidad = cantidad;
    }
}
