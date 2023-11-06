package com.bancom.retobackend.services;

import com.bancom.retobackend.dtos.PostText;
import com.bancom.retobackend.entities.Post;
import com.bancom.retobackend.entities.Usuario;
import com.bancom.retobackend.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    private final UsuarioService usuarioService;

    public PostService(PostRepository postRepository, UsuarioService usuarioService) {
        this.postRepository = postRepository;
        this.usuarioService = usuarioService;
    }

    public Post save(Post posts, Long idUser) {
        Optional<Usuario> usuario = this.usuarioService.findById(idUser);
        if (usuario.isPresent()) {
            posts.setUsuario(usuario.get());
            return postRepository.save(posts);
        }
        logger.error("id_user: %d , post : %s".formatted(idUser, posts.getText()));
        throw new NoSuchElementException("User with id : %d don't exist".formatted(idUser));
    }

    public List<Post> getPostByUser(Long idUser) {
        return this.postRepository.findByUsuarioIdUser(idUser);
    }

    public Post editPost(PostText postText, Long idPost, Long idUser) {
        Post post = this.isPostCreatedByUser(idPost, idUser);
        if (post != null) {
            post.setText(postText.getText());
            return this.postRepository.save(post);
        }
        throw new NoSuchElementException("The post can only be edited by the user who created it");

    }

    public void deletePost(Long idPost, Long idUser) {
        Post post = this.isPostCreatedByUser(idPost, idUser);
        if (post != null) {
            this.postRepository.deleteById(post.getIdPost());
        }
        else{
            throw new NoSuchElementException("The post can only be edited by the user who created it");
        }
    }

    public Post isPostCreatedByUser(Long idPost, Long idUser) {
        Post post = this.postRepository.findById(idPost).orElse(null);
        if (post != null) {
            if (idUser.equals(post.getUsuario().getIdUser())) {
                return post;
            }
        }
        return null;
    }
}
