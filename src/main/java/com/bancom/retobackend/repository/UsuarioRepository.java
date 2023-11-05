package com.bancom.retobackend.repository;
import com.bancom.retobackend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>  {
}

