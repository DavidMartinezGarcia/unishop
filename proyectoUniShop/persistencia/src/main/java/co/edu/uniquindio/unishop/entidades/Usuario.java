package co.edu.uniquindio.unishop.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @Column(nullable = false, length = 80)
    private String nombre;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @ElementCollection
    @Column(nullable = false)
    private List<String> telefonos;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Chat> chats;

    @Column(nullable = false)
    private String contrasenia;


    @ManyToOne
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Oferta> listaPujas;

    @ManyToMany
    @ToString.Exclude
    private List<Producto> listaFavoritos;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    private List<Producto> productos;

    public Usuario(Ciudad ciudad, String nombre, String email, List<String> telefonos, String contrasenia, TipoUsuario tipoUsuario) {

        this.ciudad = ciudad;
        this.nombre = nombre;
        this.email = email;
        this.telefonos = telefonos;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
    }
}
