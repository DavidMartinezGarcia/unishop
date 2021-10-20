package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mensaje implements Serializable {
    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fecha;

    @Column(nullable = false)
    private String mensaje;

    @ManyToOne
    private Chat chat;

    public Mensaje(LocalDate fecha, String mensaje) {

        this.fecha = fecha;
        this.mensaje = mensaje;
    }
}
