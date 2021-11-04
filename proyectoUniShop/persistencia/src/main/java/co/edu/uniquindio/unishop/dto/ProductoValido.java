package co.edu.uniquindio.unishop.dto;

import co.edu.uniquindio.unishop.entidades.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductoValido {

    private String nombre;
    private String descripcion;
    private Double precio;
    private Ciudad ciudad;

}
