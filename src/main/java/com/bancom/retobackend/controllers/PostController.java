package com.bancom.retobackend.controllers;
import com.bancom.retobackend.entities.Post;
import com.bancom.retobackend.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postsService;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    public PostController(PostService postsService) {
        this.postsService = postsService;
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<Post> createPost(@PathVariable(name = "idUser") Long idUser, @RequestBody Post post){
        try{
            return ResponseEntity.ok(this.postsService.save(post,idUser));
        }catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Post>> getPosts(){
        try{
            return ResponseEntity.ok(this.postsService.findAll());
        }catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
