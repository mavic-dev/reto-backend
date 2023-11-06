package com.bancom.retobackend.services;

import com.bancom.retobackend.dtos.UpdateUsuario;
import com.bancom.retobackend.entities.Usuario;
import com.bancom.retobackend.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
        Long idUser = usuario.getIdUser();
        if (usuario.getIdUser() == null || !this.usuarioRepository.existsById(idUser)) {
            return this.usuarioRepository.save(usuario);
        }
        throw new NoSuchElementException("User with id %d exist".formatted(idUser));
    }

    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    public Usuario editUser(UpdateUsuario usuarioUpdated, Long idUser) {
        Usuario user = this.findById(idUser).orElse(null);
        if(user!=null){
            BeanUtils.copyProperties(usuarioUpdated,user,getNullPropertyNames(usuarioUpdated));
            return this.usuarioRepository.save(user);
        }
        throw new NoSuchElementException("User with id %d don't exist".formatted(idUser));
    }

    public Optional<Usuario> findById(Long idUser) {
        return this.usuarioRepository.findById(idUser);
    }

    public Usuario getUsuarioById(Long idUser) {
        Usuario usuario = this.usuarioRepository.findById(idUser).orElse(null);
        if(usuario != null){
            return usuario;
        }
        throw new NoSuchElementException("User with id %d don't exist".formatted(idUser));
    }

    public void deleteUser(Long idUser) {
        Boolean existUsuario = this.usuarioRepository.existsById(idUser);
        if(existUsuario){
            this.usuarioRepository.deleteById(idUser);
        }else{
            throw new NoSuchElementException("User with id %d don't exist".formatted(idUser));
        }
    }

    private String[] getNullPropertyNames(Object source) {
        List<String> nullProperties = new ArrayList<>();
        for (Field field : source.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(source) == null) {
                    nullProperties.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return nullProperties.toArray(String[]::new);
    }
}

