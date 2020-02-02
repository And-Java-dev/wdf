package com.example.wdfrest.endPoint;

import com.example.common.model.Address;
import com.example.common.model.Image;
import com.example.common.model.Product;
import com.example.common.model.User;
import com.example.common.service.AddressService;
import com.example.common.service.ImageService;
import com.example.common.service.ProductService;
import com.example.common.service.UserService;
import com.example.common.dto.AuthenticationRequest;
import com.example.common.dto.AuthenticationResponse;
import com.example.common.dto.UserDto;
import com.example.wdfrest.security.CurrentUser;
import com.example.wdfrest.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequestMapping("/rest/users/")
@RestController
@CrossOrigin(origins = "*")
public class UserEndPoint {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final ImageService imageService;
    private final AddressService addressService;
    private final ProductService productService;


    public UserEndPoint(UserService userService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, ImageService imageService, AddressService addressService, ProductService productService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.imageService = imageService;
        this.addressService = addressService;
        this.productService = productService;

    }


    @PostMapping("auth")
    public ResponseEntity auth(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = userService.findByEmail(authenticationRequest.getEmail());
        if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(token)
                    .userDto(UserDto.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .userType(user.getUserType())
                            .phoneNumber(user.getPhoneNumber())
                            .build())
                    .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @GetMapping("activate")
    public ResponseEntity activate(@RequestParam("token") String token) {
        userService.activate(token);
        return ResponseEntity.ok().build();
    }



    @PutMapping("addImage")
    public ResponseEntity addUserImage(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam(value = "file") MultipartFile file) {
        try {
            User byId = userService.findById(currentUser.getUser().getId());
            Image image = imageService.addImage(file);
            byId.setImage(image);
            userService.save(byId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }


    @DeleteMapping
    public ResponseEntity deleteById(@AuthenticationPrincipal CurrentUser currentUser) {
        userService.deleteById(currentUser.getUser().getId());
        return ResponseEntity.ok().build();

    }

    @GetMapping("id")
    public ResponseEntity findUserById(@AuthenticationPrincipal CurrentUser user) {
        User byId = userService.findById(user.getUser().getId());
        return ResponseEntity.ok(byId);
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        if (userService.isExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        userService.save(user);
        return ResponseEntity.ok("user was success");
    }

    @PostMapping("address")
    public void saveAddress(@RequestBody Address address, @AuthenticationPrincipal CurrentUser user) {
        User byId = userService.findById(user.getUser().getId());
        byId.setAddress(address);
        addressService.save(address);
        userService.save(byId);
    }

    @PostMapping("products")
    public void addProducts(@RequestParam("products") List<Long> products, @AuthenticationPrincipal CurrentUser user) {
        User byId = userService.findById(user.getUser().getId());
        List<Product> productList = productService.getProducts(products);
        byId.setProducts(productList);
        userService.save(byId);
    }
}
