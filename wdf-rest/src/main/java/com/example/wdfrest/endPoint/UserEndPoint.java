package com.example.wdfrest.endPoint;

import com.example.common.model.Address;
import com.example.common.model.Image;
import com.example.common.model.User;
import com.example.common.service.AddressService;
import com.example.common.service.ImageService;
import com.example.common.service.UserService;
import com.example.wdfrest.dto.AuthenticationRequest;
import com.example.wdfrest.dto.AuthenticationResponse;
import com.example.wdfrest.dto.UserDto;
import com.example.wdfrest.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/rest/users/")
@RestController
public class UserEndPoint {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final ImageService imageService;
    private final AddressService addressService;

    public UserEndPoint(UserService userService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, ImageService imageService, AddressService addressService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.imageService = imageService;
        this.addressService = addressService;
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

    @PutMapping("addImage/{userId}")
    public ResponseEntity addImage(@PathVariable("userId") long userId, @RequestParam(value = "file") MultipartFile file) {
        try {
            User byId = userService.findById(userId);
            Image image = imageService.addImage(file);
            byId.setImage(image);
            userService.save(byId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("{id}")
    public ResponseEntity findUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        if (userService.isExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok("user was success");
    }

    @PostMapping("address/{userId}")
    public void saveAddress(@RequestBody Address address,@PathVariable("userId") long userId){
        User byId = userService.findById(userId);
        byId.setAddress(address);
        addressService.save(address);
        userService.save(byId);
    }
}
