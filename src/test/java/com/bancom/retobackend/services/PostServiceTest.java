package com.bancom.retobackend.services;

import com.bancom.retobackend.dtos.PostText;
import com.bancom.retobackend.entities.Post;
import com.bancom.retobackend.entities.Usuario;
import com.bancom.retobackend.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    @Mock
    UsuarioService usuarioService;

    @Test
    void save() {

        Usuario usuario = new Usuario(1L, "987654321", "Victor", "Saravia", "123456", null);
        Post post = new Post(1L, "mi primer post para usuario Victor", usuario);
        List<Post> posts = Arrays.asList(post);
        usuario.setPosts(posts);

        when(usuarioService.findById(usuario.getIdUser())).thenReturn(Optional.of(usuario));
        when(postRepository.save(post)).thenReturn(post);

        Post postResult = postService.save(post, usuario.getIdUser());
        assertNotNull(postResult);
        assertEquals(post, postResult);
    }

    @Test
    void getPostByUser() {
        Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);
        Post post1 = new Post(1L,"mi primer post para usuario Victor",usuario);
        Post post2 = new Post(2L,"mi segundo post para usuario Victor",usuario);
        List<Post> postsUsuario = Arrays.asList(post1,post2);
        usuario.setPosts(postsUsuario);

        when(postRepository.findByUsuarioIdUser(usuario.getIdUser())).thenReturn(postsUsuario);
        List<Post> result = postService.getPostByUser(usuario.getIdUser());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("mi primer post para usuario Victor", result.get(0).getText());
        assertEquals("mi segundo post para usuario Victor", result.get(1).getText());
    }

    @Test
    void isPostCreatedByUser() {
            Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);
            Post post = new Post(1L,"mi primer post para usuario Victor",usuario);
            List<Post> postsUsuario = Arrays.asList(post);
            usuario.setPosts(postsUsuario);

            when(postRepository.findById(post.getIdPost())).thenReturn(Optional.of(post));
            Post result = postService.isPostCreatedByUser(post.getIdPost(), post.getUsuario().getIdUser());

            assertNotNull(result);
            assertEquals(post.getIdPost(), result.getIdPost());
            assertEquals(post.getUsuario().getIdUser(), result.getUsuario().getIdUser());
    }

    @Test
      void editPost() {

        PostText postText = new PostText();
        String text = "updateText";
        postText.setText(text);
        Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);
        Post post = new Post(1L,"mi primer post para usuario Victor",usuario);

        when(postRepository.findById(post.getIdPost())).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(post);
        Post resultPostUpdate = postService.editPost(postText, post.getIdPost(), post.getUsuario().getIdUser());

        assertNotNull(post);
        assertEquals(post.getIdPost(),resultPostUpdate.getIdPost());
        assertEquals(postText.getText(), resultPostUpdate.getText());

    }

    @Test
    void deletePost() {
        Usuario usuario = new Usuario(1L,"987654321","Victor","Saravia","123456",null);
        Post post = new Post(1L,"mi primer post para usuario Victor",usuario);
        List<Post> postsUsuario = Arrays.asList(post);
        List<Post> postEmpty = Arrays.asList();

        when(postRepository.findByUsuarioIdUser(usuario.getIdUser())).thenReturn(postsUsuario).thenReturn(postEmpty);
        when(postRepository.findById(usuario.getIdUser())).thenReturn(Optional.of(post));

        assertEquals(1,postService.getPostByUser(1L).size());
        postService.deletePost(post.getIdPost(), usuario.getIdUser());
        assertEquals(0,postService.getPostByUser(1L).size());

    }
}