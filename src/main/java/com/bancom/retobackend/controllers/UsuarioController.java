package com.bancom.retobackend.controllers;
import com.bancom.retobackend.dtos.UpdateUsuario;
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

    @GetMapping("/{idUser}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable(name="idUser") Long idUser){
        try{
            return ResponseEntity.ok(this.usuarioService.getUsuarioById(idUser));
        }
        catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @PatchMapping("/{idUser}")
    public ResponseEntity<Usuario> updateUser(@RequestBody UpdateUsuario usuarioUpdated,@PathVariable(name = "idUser") Long idUser){
        try{
            return ResponseEntity.ok(this.usuarioService.editUser(usuarioUpdated,idUser));
        }
        catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name="idUser") Long idUser){
        try{
            this.usuarioService.deleteUser(idUser);
            logger.info("User %d is deleted".formatted(idUser));
            return ResponseEntity.ok().build();
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

