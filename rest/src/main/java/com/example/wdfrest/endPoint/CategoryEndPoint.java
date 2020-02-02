package com.example.wdfrest.endPoint;

import com.example.common.model.Category;
import com.example.common.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/categories/")
public class CategoryEndPoint {

    private final CategoryService categoryService;

    public CategoryEndPoint(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

//    @PostMapping
//    public ResponseEntity save(@RequestBody Category category){
//        categoryService.addCategory(category);
//        return ResponseEntity.ok().build();
//    }
}
