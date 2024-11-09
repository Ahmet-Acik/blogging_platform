package com.ahmet.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.time.LocalDateTime;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
        name = "post_category",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    public void updateFrom(Post newPost) {
        this.title = newPost.getTitle();
        this.content = newPost.getContent();
        this.createdAt = newPost.getCreatedAt();
        this.updatedAt = newPost.getUpdatedAt();
        this.user = newPost.getUser();
        this.comments.clear();
        this.comments.addAll(newPost.getComments());
        this.categories.clear();
        this.categories.addAll(newPost.getCategories());
    }
}