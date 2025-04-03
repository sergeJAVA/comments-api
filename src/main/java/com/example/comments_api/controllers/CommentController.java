package com.example.comments_api.controllers;

import com.example.comments_api.model.Comment;
import com.example.comments_api.model.mapper.CommentMapper;
import com.example.comments_api.services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ObjectMapper objectMapper;

    private final CommentService commentService;

    private final CommentMapper commentMapper;


    // Получить все комментарии
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAll();
        return ResponseEntity.ok(comments);
    }

    // Получить комментарий по ID
    @GetMapping("/{id}")
    public <T> ResponseEntity<T> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            return (ResponseEntity<T>) new ResponseEntity<>(commentMapper.commentToDto(comment.get()), HttpStatus.OK);
        }

        return (ResponseEntity<T>) new ResponseEntity<>("Comment doesn't exist", HttpStatus.NOT_FOUND);

    }

    // Получить все комментарии для поста по postId
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.findAllByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Создать новый комментарий
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.status(201).body(createdComment); // 201 Created
    }

    // Удалить комментарий по ID и postId
    @DeleteMapping("/{commentId}/post/{postId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @PathVariable Long postId) {
        if (commentService.existsByIdAndPostId(commentId, postId)) {
            commentService.deleteComment(commentId, postId);
            return ResponseEntity.ok("Comment with ID \"" + commentId + "\" was deleted from post with ID \"" + postId + "\"");
        }

        return new ResponseEntity<>("Comment was not found in post with id \"" + postId + "\"", HttpStatus.NOT_FOUND); // 204 No Content
    }
}
