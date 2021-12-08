package co.edu.uniquindio.unishop.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
    @JsonIgnore
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;

    @Column(nullable = false, length = 80)
    @Length(max = 80)
    private String nombre;

    @Column(nullable = false, unique = true, length = 120)
    @Length(max = 120)
    @Email(message = "Por favor ingrese un email válido.")
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    @JsonIgnore
    private List<String> telefonos;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Chat> chats;

    @Column(nullable = false, length = 80)
    @Length(max = 80,min = 6, message = "Longitud no válida para la contraseña")
    private String contrasenia;


    @ManyToOne
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Oferta> listaPujas;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> listaFavoritos;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productos;

    public Usuario(Ciudad ciudad, String nombre, String email, List<String> telefonos, String contrasenia, TipoUsuario tipoUsuario) {

        this.ciudad = ciudad;
        this.nombre = nombre;
        this.email = email;
        this.telefonos = telefonos;
        this.contrasenia = contrasenia;
        this.tipoUsuario = tipoUsuario;
        listaFavoritos = new ArrayList<>();
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
