package com.example.library.serivce;

//import com.example.library.dto.CategoryDto;
import com.example.library.dto.CategoryDto;
import com.example.library.model.Category;
import com.example.library.model.Product;
import com.example.library.model.Category;

import java.util.List;

public interface CategoryService {
    /*Admin*/
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();

    List<CategoryDto> getCategoryAndProduct();

    /*Customer*/
//  List<CategoryDto> getCategoryAndProduct();


}