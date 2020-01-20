package com.example.common.service;

import com.example.common.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> findAll();

    void register(User user, MultipartFile multipartFile) throws IOException;

    void activate(String token);

    boolean isEmailExists(String email);

    User findById(long id);

    boolean isExists(String email);

    void deleteById(long id);

    User findByEmail(String email);

}
