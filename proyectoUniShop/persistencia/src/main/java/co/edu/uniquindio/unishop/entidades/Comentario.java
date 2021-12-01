package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Usuario usuario;

    @Column(nullable = false)
    @Min(0)
    @Max(5)
    private Integer puntuacion;

    @Column(nullable = false)
    @NotBlank
    private String comentario;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fecha;

    @Column
    private String respuesta;

    public Comentario(Producto producto, Usuario usuario,String comentario, Integer puntuacion, LocalDate fecha) {

        this.producto = producto;
        this.usuario = usuario;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
    }
}
