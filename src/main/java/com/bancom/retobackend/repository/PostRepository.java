package com.bancom.retobackend.repository;
import com.bancom.retobackend.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUsuarioIdUser(Long idUser);

}
