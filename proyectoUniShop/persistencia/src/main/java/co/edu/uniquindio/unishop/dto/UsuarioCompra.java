package co.edu.uniquindio.unishop.dto;

import co.edu.uniquindio.unishop.entidades.DetalleCompra;
import co.edu.uniquindio.unishop.entidades.MetodoPago;
import co.edu.uniquindio.unishop.entidades.Producto;
import co.edu.uniquindio.unishop.entidades.Usuario;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioCompra {

    @EqualsAndHashCode.Include

    private MetodoPago metodoPago;
    private LocalDate fechaCompra;
    private Integer codigo;
    private List<DetalleCompra> detalle;

}
