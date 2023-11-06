package com.bancom.retobackend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "Usuario")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Monitoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", nullable = false)
    private Long idUser;

    @Column(name="Cellphone",length = 14, nullable = false )
    private String cellphone;

    @Column(name="Name",length = 50, nullable = false)
    private String name;

    @Column(name="LastName",length = 50, nullable = false)
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="Password",length = 30, nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JsonManagedReference// serialize and don't reference infinite recursion
    private List<Post> posts;

}
