package com.example.comments_api.controllers;

import com.example.comments_api.model.Comment;
import com.example.comments_api.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/all")
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @PostMapping("/create")
    public Comment create(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}
