package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.CategoryDTO;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryService {
    public Boolean add(String name) {
        if(name == null || name.isBlank() || name.trim().length() < 3) {
            return false;
        }
        CategoryDTO categoryDTO = ComponentContainer.categoryRepository.getCategoryByName(name);
        if(categoryDTO != null) {
            return false;
        }
        CategoryDTO category = new CategoryDTO();
        category.setName(name);
        category.setVisible(true);
        category.setCreatedAt(LocalDateTime.now());
        return ComponentContainer.categoryRepository.create(category);
    }

    public List<CategoryDTO> getCategoryLists() {
        var list = ComponentContainer.categoryRepository.categoryLists();
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }

    public Boolean delete(Integer id) {
        CategoryDTO categoryDTO = ComponentContainer.categoryRepository.getCategoryById(id);
        if(categoryDTO == null) {
            return false;
        }
        return ComponentContainer.categoryRepository.delete(id);
    }
}
