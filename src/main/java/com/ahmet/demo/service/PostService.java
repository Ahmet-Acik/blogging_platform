package com.ahmet.demo.service;

import com.ahmet.demo.dto.PostDTO;
import com.ahmet.demo.model.Post;
import java.util.List;

// PostService.java
public interface PostService {
    PostDTO savePost(Post post);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long id);
    PostDTO updatePost(Long id, Post post);
    void deletePost(Long id);
}