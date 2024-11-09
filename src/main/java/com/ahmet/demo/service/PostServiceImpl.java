package com.ahmet.demo.service;

import com.ahmet.demo.dto.PostDTO;
import com.ahmet.demo.mapper.MainMapper;
import com.ahmet.demo.model.Post;
import com.ahmet.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MainMapper mainMapper;

    @Override
    public PostDTO savePost(Post post) {
        Post savedPost = postRepository.save(post);
        return mainMapper.postToPostDTO(savedPost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(mainMapper::postToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        return postRepository.findById(id)
                .map(mainMapper::postToPostDTO)
                .orElse(null);
    }

    @Override
    public PostDTO updatePost(Long id, Post post) {
        if (postRepository.existsById(id)) {
            post.setId(id);
            Post updatedPost = postRepository.save(post);
            return mainMapper.postToPostDTO(updatedPost);
        }
        return null;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}