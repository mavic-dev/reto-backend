package com.bancom.retobackend.services;

import com.bancom.retobackend.entities.Usuario;
import com.bancom.retobackend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario save(Usuario usuario){
        Long idUser = usuario.getId_user();
        if (usuario.getId_user() == null || !this.usuarioRepository.existsById(idUser)) {
            return this.usuarioRepository.save(usuario);
        }
        throw new NoSuchElementException("User with id %d exist".formatted(idUser));
    }

    public Optional<Usuario> findById(Long id){
        return this.usuarioRepository.findById(id);
    }
    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }
}

