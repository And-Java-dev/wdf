package com.example.wdfrest.endPoint;

import com.example.common.model.Category;
import com.example.common.model.Order;
import com.example.common.model.Product;
import com.example.common.service.ImageService;
import com.example.common.service.MaterialService;
import com.example.common.service.OrderService;
import com.example.common.service.ProductService;
import com.example.wdfrest.security.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RequestMapping("/rest/products/")
@RestController
public class ProductEndPoint {

    private final ProductService productService;
    private final OrderService orderService;

    public ProductEndPoint(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;

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

//    @GetMapping("a")
//    public ResponseEntity a(){
//        List<Order> allByDeadline_dayOfMonth = orderService.findAllByDeadline_DayOfMonth();
//        return ResponseEntity.ok(allByDeadline_dayOfMonth);
//
//    }

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
