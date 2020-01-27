package com.example.wdfrest.endPoint;

import com.example.common.model.Category;
import com.example.common.model.Image;
import com.example.common.model.Material;
import com.example.common.model.Product;
import com.example.common.service.ImageService;
import com.example.common.service.MaterialService;
import com.example.common.service.ProductService;
import com.example.wdfrest.security.CurrentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/rest/products/")
@RestController
public class ProductEndPoint {

    private final ProductService productService;
    private final ImageService imageService;
    private final MaterialService materialService;

    public ProductEndPoint(ProductService productService, ImageService imageService, MaterialService materialService) {
        this.productService = productService;
        this.imageService = imageService;
        this.materialService = materialService;
    }

    @PutMapping
    public ResponseEntity createProduct(@RequestBody Product product, @RequestParam("images") MultipartFile[] file,
                                        @RequestParam("height") double height, @RequestParam("width") double width,
                                        @RequestParam("answer") String answer, @RequestParam("category") Category category,
                                        @RequestParam("materials") List<Long> materials, @RequestParam("title") String title,
                                        @RequestParam("count") int count, @RequestParam("description") String desc, @AuthenticationPrincipal CurrentUser user) {
        return ResponseEntity.ok(productService.createProduct(file, height, width, materials, product, answer, category, title, desc, count,user.getUser()));
    }

//    @PostMapping
//    public ResponseEntity save(@RequestBody Product product) {
//        productService.save(product);
//        return ResponseEntity.ok().build();
//
//    }

//    @PutMapping(value = "addImages/{productId}")
//    public ResponseEntity addImages(@PathVariable("productId") long productId, @RequestParam(value = "images") MultipartFile[] file) {
//        try {
//            Product byId = productService.findById(productId);
//            List<Image> imageList = imageService.addImages(file);
//            byId.setImages(imageList);
//            productService.save(byId);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//        return ResponseEntity.ok().build();
//    }

//    @PutMapping(value = "addMaterials/{productId}")
//    public ResponseEntity addImages(@PathVariable("productId") long productId, @RequestParam(value = "materials")List<Material> materials) {
//            Product byId = productService.findById(productId);
//            List<Material> materialList = materialService.saveMaterials(materials);
//            byId.setMaterials(materialList);
//            productService.save(byId);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();

    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cat")
    public List<Product> findAllByCategory(@RequestParam("title") String title) {
        return productService.findAllByCategory(title);
    }

    @GetMapping("/material")
    public List<Product> findAllByMaterialTitle(@RequestParam("material_title") String materials_title) {
        return productService.findAllByMaterials(materials_title);
    }
}
