package com.example.wdfrest.endPoint;


import com.example.common.model.Image;
import com.example.common.service.ImageService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.util.List;

@RequestMapping("/rest/images/")
@RestController
public class ImageEndPoint {


    private final ImageService imageService;

    public ImageEndPoint(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("imageUrl") String imageUrl) {
        return imageService.getImage(imageUrl);
    }

    @GetMapping("/product")
    public List<Image> findAllByProductId(@RequestParam("product_id") long product_id) {
        return imageService.findAllByProductId(product_id);

    }

    @GetMapping("/material")
    public List<Image> findAllByMaterialId(@RequestParam("material_id") long material_id) {
        return imageService.findAllByMaterialId(material_id);
    }

    @PostMapping
     public ResponseEntity addImages(@RequestParam("files")  MultipartFile[] files) {
        List<Image> imageList = null;
        try {
             imageList = imageService.addImages(files);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(imageList);
    }

    @PostMapping("addImage")
    public ResponseEntity addImage(@RequestBody MultipartFile multipartFile) {
        Image image = null;
        try {
            image = imageService.addImage(multipartFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(image);
    }

    @GetMapping("all")
    public List<Image> findAll() {
        return imageService.findAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") long id){
        imageService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") long id){
        return ResponseEntity.ok(imageService.findById(id));
    }
}
