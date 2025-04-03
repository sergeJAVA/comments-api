package com.example.comments_api.repository;

import com.example.comments_api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByPostId(Long postId);
    boolean existsByIdAndPostId(Long id, Long postId);
    void deleteByIdAndPostId(Long id, Long postId);
}
