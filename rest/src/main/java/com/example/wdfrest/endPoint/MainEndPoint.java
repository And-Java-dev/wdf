package com.example.wdfrest.endPoint;

import com.example.common.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/")
@RestController
public class MainEndPoint {

    private final UserService userService;

    public MainEndPoint(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("userProfile/{userId}")
    public ResponseEntity profile(@PathVariable("userId") long id){
        return ResponseEntity.ok().build();
    }

    @GetMapping("userWishList/{userId}")
    public ResponseEntity wishList(@PathVariable("userId") long id){
        return ResponseEntity.ok().build();
    }
}
