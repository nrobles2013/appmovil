package com.popularsafi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuario_id;

    @Column(nullable = false, length = 11)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String apellido;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false, length = 15)
    private String telefono;

    @Column(length = 1)
    private String tpparticipe;

    @Column(length = 5)
    private String cparticipe;

    @Column( length = 15)
    private String telcorpora;

    @Column( length = 15)
    private String celular;

    private Date fregistro;


    @Column( length = 4)
    private Integer numintentos;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    )





    private Set<Role> roles = new HashSet<>();
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
/*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_usuario",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    )


*/

    public Usuario(String nombre, String apellido,String username,  String email,  String password,
                   String telefono,String celular,String tipoparticipe,Boolean enabled,Integer numintentos,String cparticipe) {
        this.nombre = nombre;
        this.apellido=apellido;
        this.username = username;
        this.email = email;
        this.telefono=telefono;
        this.celular=celular;
        this.password = password;
        this.tpparticipe=tipoparticipe;
        this.enabled=enabled;
        this.numintentos=numintentos;
        this.cparticipe=cparticipe;
    }

    public Usuario(Integer usuario_id,  String password) {
        this.usuario_id = usuario_id;
        this.password = password;

    }
}
