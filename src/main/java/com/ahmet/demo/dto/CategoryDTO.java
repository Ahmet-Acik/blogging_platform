package com.ahmet.demo.dto;

import com.ahmet.demo.model.CategoryType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private CategoryType name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
    private List<PostDTO> posts;
}