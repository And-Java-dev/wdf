package com.example.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column
    private LocalTime time;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @Column
    private double price;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime deadline;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.NEW;



}
