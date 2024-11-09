package com.ahmet.demo.service;

import com.ahmet.demo.dto.CategoryDTO;
import com.ahmet.demo.model.Category;
import com.ahmet.demo.repository.CategoryRepository;
import com.ahmet.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}