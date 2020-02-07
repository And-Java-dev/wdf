package com.example.common.dto;

import com.example.common.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private UserType userType;
    private String phoneNumber;



}