package co.edu.uniquindio.unishop.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubastaProducto {
    @EqualsAndHashCode.Include
    private Integer id;
    private String imagen;
    private String nombre;
    private Integer tiempoLimite;
    private Double ofertaMayor;
    private Integer numeroOfertas;
}
