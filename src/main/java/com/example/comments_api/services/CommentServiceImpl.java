package com.example.comments_api.services;

import com.example.comments_api.model.Comment;
import com.example.comments_api.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        Comment newComment = Comment.builder()
                .author(comment.getAuthor())
                .text(comment.getText())
                .postId(comment.getPostId())
                .timestamp(LocalDateTime.now())
                .build();
        return commentRepository.save(newComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<List<Comment>> findAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public void saveAll(List<Comment> list) {
        commentRepository.saveAll(list);
    }


}
