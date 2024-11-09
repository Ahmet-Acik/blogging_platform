package com.ahmet.demo.mapper;

import com.ahmet.demo.dto.CategoryDTO;
import com.ahmet.demo.dto.CommentDTO;
import com.ahmet.demo.dto.PostDTO;
import com.ahmet.demo.dto.UserDTO;
import com.ahmet.demo.model.Category;
import com.ahmet.demo.model.Comment;
import com.ahmet.demo.model.Post;
import com.ahmet.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MainMapper {

   MainMapper INSTANCE = Mappers.getMapper(MainMapper.class);

    PostDTO postToPostDTO(Post post);

    UserDTO userToUserDTO(User user);

    CommentDTO commentToCommentDTO(Comment comment);

    CategoryDTO categoryToCategoryDTO(Category category);
}