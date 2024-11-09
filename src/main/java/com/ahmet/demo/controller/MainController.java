package com.ahmet.demo.controller;

import com.ahmet.demo.model.User;
import com.ahmet.demo.model.Post;
import com.ahmet.demo.model.Comment;
import com.ahmet.demo.model.Category;
import com.ahmet.demo.service.UserService;
import com.ahmet.demo.service.PostService;
import com.ahmet.demo.service.CommentService;
import com.ahmet.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Main Controller", description = "Controller for managing users, posts, comments, and categories")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CategoryService categoryService;

    // User endpoints
    @PostMapping("/users")
    @Operation(summary = "Create a new user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Update a user by ID")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete a user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Post endpoints
    @PostMapping("/posts")
    @Operation(summary = "Create a new post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping("/posts")
    @Operation(summary = "Get all posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    @Operation(summary = "Get a post by ID")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/posts/{id}")
    @Operation(summary = "Update a post by ID")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(id, post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{id}")
    @Operation(summary = "Delete a post by ID")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // Comment endpoints
    @PostMapping("/comments")
    @Operation(summary = "Create a new comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @GetMapping("/comments")
    @Operation(summary = "Get all comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comments/{id}")
    @Operation(summary = "Get a comment by ID")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/comments/{id}")
    @Operation(summary = "Update a comment by ID")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(id, comment);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/comments/{id}")
    @Operation(summary = "Delete a comment by ID")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    // Category endpoints
    @PostMapping("/categories")
    @Operation(summary = "Create a new category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{id}")
    @Operation(summary = "Get a category by ID")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/categories/{id}")
    @Operation(summary = "Update a category by ID")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/categories/{id}")
    @Operation(summary = "Delete a category by ID")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}