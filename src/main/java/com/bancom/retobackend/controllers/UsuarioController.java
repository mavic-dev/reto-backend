package com.bancom.retobackend.controllers;
import com.bancom.retobackend.entities.Usuario;
import com.bancom.retobackend.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
        try{
            return ResponseEntity.ok(this.usuarioService.save(usuario));
        }
        catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers(){
        try {
            return ResponseEntity.ok(this.usuarioService.findAll());
        }catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}

