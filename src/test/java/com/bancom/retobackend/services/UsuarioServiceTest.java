package com.bancom.retobackend.services;

import com.bancom.retobackend.dtos.UpdateUsuario;
import com.bancom.retobackend.entities.Usuario;
import com.bancom.retobackend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioService usuarioService;
    @Test
    void save() {
        Usuario usuario = new Usuario(1L, "987654321", "Victor", "Saravia", "123456", null);

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario usuarioResult = usuarioService.save(usuario);
        assertNotNull(usuarioResult);
        assertEquals(usuario, usuarioResult);
    }

    @Test
    void findAll() {
        Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);
        Usuario usuario2 = new Usuario(2L,"12345678","Manuel","Tinco","abc123%$",null);
        List<Usuario> usuarios = Arrays.asList(usuario,usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> usuariosResult = usuarioService.findAll();

        assertNotNull(usuariosResult);
        assertEquals(usuarios, usuariosResult);
    }

    @Test
    void editUser() {
        UpdateUsuario usuarioUpdated = new UpdateUsuario();
        usuarioUpdated.setCellphone("111111111");

        Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);

        when(usuarioRepository.findById(usuario.getIdUser())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario resultUsuario = usuarioService.editUser(usuarioUpdated,usuario.getIdUser());

        assertNotNull(resultUsuario);
        assertEquals(resultUsuario.getCellphone(),usuario.getCellphone());
    }

    @Test
    void findById() {
        Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);

        when(usuarioRepository.findById(usuario.getIdUser())).thenReturn(Optional.of(usuario));
        Usuario usuarioResult = usuarioService.findById(usuario.getIdUser()).get();

        assertNotNull(usuarioResult);
        assertEquals(usuario, usuarioResult);
    }

    @Test
    void getUsuarioById() {
        Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);

        when(usuarioRepository.findById(usuario.getIdUser())).thenReturn(Optional.of(usuario));
        Usuario usuarioResult = usuarioService.getUsuarioById(usuario.getIdUser());

        assertNotNull(usuarioResult);
        assertEquals(usuario, usuarioResult);
    }

    @Test
    void deleteUser() {

        Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);

        when(usuarioRepository.existsById(usuario.getIdUser())).thenReturn(true);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario)).thenReturn(Optional.empty());

        assertNotNull(usuarioService.getUsuarioById(1L));
        usuarioService.deleteUser( usuario.getIdUser());
        assertThrows(NoSuchElementException.class,() -> usuarioService.getUsuarioById( usuario.getIdUser() ));
    }
}