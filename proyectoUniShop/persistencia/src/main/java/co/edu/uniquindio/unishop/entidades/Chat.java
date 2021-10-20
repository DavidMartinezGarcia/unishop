package co.edu.uniquindio.unishop.entidades;

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
    private List<Mensaje> mensajes;


}
