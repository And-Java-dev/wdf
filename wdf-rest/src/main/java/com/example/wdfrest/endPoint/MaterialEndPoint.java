package com.example.wdfrest.endPoint;


import com.example.common.model.Category;
import com.example.common.model.Material;
import com.example.common.service.CategoryService;
import com.example.common.service.ImageService;
import com.example.common.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/materials")
public class MaterialEndPoint {

    private final MaterialService materialService;



    public MaterialEndPoint(MaterialService materialService) {
        this.materialService = materialService;

    }


    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") long id){
        Material byId = materialService.findById(id);
        return ResponseEntity.ok(byId);
    }

    @GetMapping
    public List<Material> findAll(){
         return materialService.findAll();
    }

    @GetMapping("findByCategory")
    public List<Material> findByCategory(@RequestParam("categoryId") long cat_id){
        return materialService.findAllByCategoriesId(cat_id);
    }

    @GetMapping("findByProduct")
    public List<Material> findByProduct(@RequestParam("productId") long prod_id){
        return materialService.findAllByProductsId(prod_id);
    }

    @GetMapping("findByCatTitle")
    public List<Material> findByMaterialCatTitle(@RequestParam("title") String title){
        return materialService.findAllByMaterialCategoryTitle(title);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity deleteById(@PathVariable("id") long id){
//        materialService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }



}
