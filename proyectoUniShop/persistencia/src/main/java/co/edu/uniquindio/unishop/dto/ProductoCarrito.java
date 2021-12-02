package co.edu.uniquindio.unishop.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductoCarrito {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre, imagen;
    private Double precio;
    private Integer unidades;

}
