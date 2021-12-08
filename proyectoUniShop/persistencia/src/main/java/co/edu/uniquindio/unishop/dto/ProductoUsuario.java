package co.edu.uniquindio.unishop.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductoUsuario {
    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre;
    private String imagen;
    private Double precio;
    private Integer cantidadDisponible;


}
