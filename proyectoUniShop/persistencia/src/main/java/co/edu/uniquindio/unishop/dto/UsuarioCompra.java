package co.edu.uniquindio.unishop.dto;

import co.edu.uniquindio.unishop.entidades.MetodoPago;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioCompra {

    @EqualsAndHashCode.Include
    private MetodoPago metodoPago;
    private LocalDate fechaCompra;

}
