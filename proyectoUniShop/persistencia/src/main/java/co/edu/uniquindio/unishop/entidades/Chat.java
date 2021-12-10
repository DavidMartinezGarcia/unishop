package co.edu.uniquindio.unishop.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Chat implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoChat;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "chat")
    @ToString.Exclude
    @JsonIgnore
    private List<Mensaje> mensajes;

    @OneToOne
    @ToString.Exclude
    @JsonIgnore
    private DetalleCompra codigoProducto;

    public Chat(Usuario usuario, List<Mensaje> mensajes, DetalleCompra codigoProducto) {
        this.usuario = usuario;
        this.mensajes = mensajes;
        this.codigoProducto = codigoProducto;
    }
}
