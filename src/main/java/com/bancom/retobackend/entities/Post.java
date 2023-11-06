package com.bancom.retobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Posts")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Post extends Monitoring{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Long idPost;

    @Column(name="Text",nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    @JsonBackReference//  serialize and don't reference infinite recursion
    private Usuario usuario;

}

