package com.davideleonino.palestra.service;

import com.davideleonino.palestra.dto.request.CourseCategoryRequest;
import com.davideleonino.palestra.dto.response.CourseCategoryResponse;
import com.davideleonino.palestra.model.CourseCategory;
import com.davideleonino.palestra.repository.CourseCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseCategoryService {

    private final CourseCategoryRepository categoryRepository;


    public CourseCategoryService(CourseCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CourseCategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public CourseCategoryResponse getCategoryById(Long id) {
        CourseCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con ID: " + id));
        return convertToResponse(category);
    }

    public CourseCategoryResponse createCategory(CourseCategoryRequest request) {
        CourseCategory category = new CourseCategory();
        category.setName(request.getName());
        CourseCategory saved = categoryRepository.save(category);
        return convertToResponse(saved);
    }

    public CourseCategoryResponse updateCategory(Long id, CourseCategoryRequest request) {
        CourseCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con ID: " + id));

        category.setName(request.getName());
        CourseCategory updated = categoryRepository.save(category);
        return convertToResponse(updated);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoria non trovata con ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CourseCategoryResponse convertToResponse(CourseCategory category) {
        CourseCategoryResponse response = new CourseCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
}