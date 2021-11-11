package co.edu.uniquindio.unishop.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @EqualsAndHashCode.Include
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
    @Length(max = 80)
    private String nombre;

    @Column(nullable = false, unique = true, length = 120)
    @Length(max = 120)
    @Email
    private String email;

    @ElementCollection
    @Column(nullable = false)
    private List<String> telefonos;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Chat> chats;

    @Column(nullable = false, length = 80)
    @Length(max = 80,min = 6)
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

    public Usuario(Integer codigo,Ciudad ciudad, String nombre, String email, List<String> telefonos, String contrasenia, TipoUsuario tipoUsuario) {
        this.codigo = codigo;
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.email = email;
        this.telefonos = telefonos;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
    }
}
