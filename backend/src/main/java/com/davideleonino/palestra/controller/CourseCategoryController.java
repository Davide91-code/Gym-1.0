package com.davideleonino.palestra.controller;

import com.davideleonino.palestra.dto.request.CourseCategoryRequest;
import com.davideleonino.palestra.dto.response.CourseCategoryResponse;
import com.davideleonino.palestra.service.CourseCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CourseCategoryController {

    private final CourseCategoryService categoryService;

    public CourseCategoryController(CourseCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //  GET tutte le categorie
    @GetMapping
    public ResponseEntity<List<CourseCategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //  GET singola categoria (opzionale, ma utile)
    @GetMapping("/{id}")
    public ResponseEntity<CourseCategoryResponse> getCategoryById(@PathVariable Long id) {
        CourseCategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    //  POST nuova categoria
    @PostMapping
    public ResponseEntity<CourseCategoryResponse> createCategory(@RequestBody CourseCategoryRequest request) {
        CourseCategoryResponse created = categoryService.createCategory(request);
        return ResponseEntity.ok(created);
    }

    //  PUT modifica categoria
    @PutMapping("/{id}")
    public ResponseEntity<CourseCategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CourseCategoryRequest request) {
        CourseCategoryResponse updated = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(updated);
    }

    //  DELETE elimina categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}