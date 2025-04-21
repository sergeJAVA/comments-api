package com.example.comments_api.services;

import com.example.comments_api.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll();

    Optional<Comment> findById(Long id);
    Comment createComment(Comment comment, String token);
    List<Comment> findAllByPostId(Long postId);
    void saveAll(List<Comment> list);
    void deleteComment(Long commentId, Long postId);
    boolean existsByIdAndPostId(Long id, Long postId);
    void deleteCommentsByPostId(Long postId);
}
