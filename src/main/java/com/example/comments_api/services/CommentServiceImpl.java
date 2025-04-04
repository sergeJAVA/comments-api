package com.example.comments_api.services;

import com.example.comments_api.model.Comment;
import com.example.comments_api.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    @Override
    @Cacheable(value = "commentLists", key = "'all'", unless = "#result.isEmpty()")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    @Cacheable(value = "comments", key = "#id", unless = "#result == null")
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "commentLists", allEntries = true),
            @CacheEvict(value = "comments", key = "#comment.postId")
    })
    public Comment createComment(Comment comment) {
        Comment newComment = Comment.builder()
                .author(comment.getAuthor())
                .text(comment.getText())
                .postId(comment.getPostId())
                .timestamp(LocalDateTime.now())
                .build();
        return commentRepository.save(newComment);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "comments", key = "#postId"),
            @CacheEvict(value = "commentLists", allEntries = true)
    })
    public void deleteComment(Long commentId, Long postId) {
        if (existsByIdAndPostId(commentId, postId)) {
            commentRepository.deleteByIdAndPostId(commentId, postId);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "commentLists", key = "#postId")
    public void deleteCommentsByPostId(Long postId) {
        commentRepository.deleteByPostId(postId);
    }

    @Override
    @Cacheable(value = "commentLists", key = "#postId", unless = "#result.isEmpty()")
    public List<Comment> findAllByPostId(Long postId) {
        return commentRepository.findByPostId(postId).orElse(Collections.emptyList());
    }

    @Override
    public void saveAll(List<Comment> list) {
        commentRepository.saveAll(list);
    }

    @Override
    public boolean existsByIdAndPostId(Long id, Long postId) {
        return commentRepository.existsByIdAndPostId(id, postId);
    }
}
