package com.dprsnn.UtilPlast.services;

import com.dprsnn.UtilPlast.models.PlasticCategory;
import com.dprsnn.UtilPlast.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<PlasticCategory> categoryList(){
        return categoryRepository.findAll();
    }

    public void addCategory(String category){
        PlasticCategory plasticCategory = new PlasticCategory();
        plasticCategory.setCategory(category);

        categoryRepository.save(plasticCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public PlasticCategory getCategoryById(Long id){
        return categoryRepository.findPlasticCategoryById(id);
    }

    public void saveCategory(Long id, String newCategory) {
        PlasticCategory plasticCategory = categoryRepository.findPlasticCategoryById(id);
        plasticCategory.setCategory(newCategory);

        categoryRepository.save(plasticCategory);
    }
}
