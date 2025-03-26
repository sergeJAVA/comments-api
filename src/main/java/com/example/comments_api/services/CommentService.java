package com.example.comments_api.services;

import com.example.comments_api.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll();

    Optional<Comment> findById(Long id);
    Comment save(Comment comment);
    void deleteById(Long id);
    Optional<List<Comment>> findAllByPostId(Long postId);
    void saveAll(List<Comment> list);
}
