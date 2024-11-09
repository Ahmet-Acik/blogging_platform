package com.ahmet.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PostDTO post;
    private UserDTO user;
    private boolean approved;

}