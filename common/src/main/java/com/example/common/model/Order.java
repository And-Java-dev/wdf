package com.example.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column
    private LocalTime time;

    @Column
    private Date date;

    @Column
    private LocalDateTime deadline;

    @OneToMany
    private List<Product> products;

    @ManyToOne
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.NEW;



}
