package com.example.common.service.impl;

import com.example.common.model.Address;
import com.example.common.model.Image;
import com.example.common.model.User;
import com.example.common.repository.ImageRepository;
import com.example.common.repository.UserRepository;
import com.example.common.service.EmailService;
import com.example.common.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {



    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final ImageRepository imageRepository;



    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService,ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.imageRepository = imageRepository;

    }

    @Override
    public void register(User user, MultipartFile multipartFile, Address address) throws IOException {
        String imageName = multipartFile.getOriginalFilename();
        imageName = UUID.randomUUID() + "_" + imageName;
        File file = new File(imageUploadDir, imageName);
        multipartFile.transferTo(file);
        Image image = new Image();
        image.setName(imageName);
        imageRepository.save(image);
        user = User.builder()
                .password(passwordEncoder.encode(user.getPassword()))
                .isEnable(false)
                .token(UUID.randomUUID().toString())
                .image(image)
                .address(address)
                .build();

        userRepository.save(user);
        String link = "http://localhost:8080/admin/users/aktivate?token=" + user.getToken();
        emailService.sendSimpleMessage(user.getEmail(),
                "Welcome",
                "Congratulations! Dear " + user.getName() + " " + user.getSurname() + " have successfully register to system! \n" +
                        "You have to activate your account by this link " + link);
    }

    @Override
    public void activate(String token) {
        User byToken = userRepository.findByToken(token);
        if (byToken != null) {
            byToken.setEnable(true);
            byToken.setToken(null);
            userRepository.save(byToken);
            emailService.sendMessageWithAttachment(byToken.getEmail(), "Success", "You success registered", "C:\\Users\\And\\Desktop\\success.jpg");
        }
    }


    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    public boolean isExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.orElse(null);
    }

    @Override
    public void returnPassword(String email, long userId) {
        User one = userRepository.getOne(userId);
        emailService.sendSimpleMessage(one.getEmail(),
                "Dear " + one.getName() + " " + one.getSurname() +"this is your password     ", one.getPassword());
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnable(false);
        user.setToken(UUID.randomUUID().toString());
        userRepository.save(user);
        String link = "http://localhost:8011/rest/users/aktivate?token=" + user.getToken();
        emailService.sendSimpleMessage(user.getEmail(),
                "Welcome",
                "Congratulations! Dear " + user.getName() + " " + user.getSurname() + " have successfully register to system! \n" +
                        "You have to activate your account by this link " + link);
    }
}
