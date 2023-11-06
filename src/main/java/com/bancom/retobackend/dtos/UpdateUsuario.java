package com.bancom.retobackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUsuario {
    private String cellphone;
    private String name;
    private String lastName;
    private String password;
}
