package com.example.comments_api.model.mapper;

import com.example.comments_api.model.Comment;
import com.example.comments_api.model.dto.CommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentDto commentToDto(Comment comment) {
        return CommentDto.builder()
                .author(comment.getAuthor())
                .text(comment.getText())
                .timestamp(comment.getTimestamp())
                .postId(comment.getPostId())
                .build();
    }
}
