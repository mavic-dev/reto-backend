package com.bancom.retobackend.controllers;
import com.bancom.retobackend.dtos.PostText;
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

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Post>> getPostByUser(@PathVariable(name = "idUser") Long idUser){
        try{
            return ResponseEntity.ok(this.postsService.getPostByUser(idUser));
        }catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("{idPost}/{idUser}")
    public ResponseEntity<Post> updatePost(
            @PathVariable(name = "idPost") Long idPost,
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody PostText postText){
        try{
            return ResponseEntity.ok(this.postsService.editPost(postText,idPost,idUser));
        }catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{idPost}/{idUser}")
    public ResponseEntity<Void> deletePost(
        @PathVariable(name = "idPost") Long idPost,
        @PathVariable(name = "idUser") Long idUser
        ){
        try {
            if(this.postsService.deletePost(idPost,idUser)){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.internalServerError().build();
            }
        }catch(RuntimeException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}