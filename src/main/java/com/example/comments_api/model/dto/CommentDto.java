package com.example.comments_api.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentDto {
    private String author;
    private String text;
    private LocalDateTime timestamp;
    private Long postId;
}
