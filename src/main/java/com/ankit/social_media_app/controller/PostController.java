package com.ankit.social_media_app.controller;

import com.ankit.social_media_app.models.Post;
import com.ankit.social_media_app.response.ApiResponse;
import com.ankit.social_media_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/post/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception {

        Post createdPost = postService.createNewPost(post,userId);

        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {

        String message = postService.deletePost(postId,userId);
        ApiResponse res = new ApiResponse(message,true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> getPostByIdHandler(@PathVariable Integer postId) throws Exception {

        Post post = postService.findPostById(postId);

        return  new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){

        List<Post> post = postService.findPostByUserId(userId);

        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);

    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost(){

        List<Post> post = postService.findAllPost();

        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);

    }

    @PutMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {

        Post post = postService.savedPost(postId,userId);

        return  new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {

        Post post = postService.likePost(postId,userId);

        return  new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }




}
