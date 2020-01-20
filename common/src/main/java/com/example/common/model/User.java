package com.example.common.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @OneToOne
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "passport_id")
    private String  passportId;

    @Enumerated(EnumType.STRING)
    @Column
    private UserType userType=UserType.USER;

    @Column
    private boolean isEnable;

    @Column
    private String token;

    @OneToOne
    private Image image;



}
