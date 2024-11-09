package com.ahmet.demo.service;

import com.ahmet.demo.model.Category;
import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}