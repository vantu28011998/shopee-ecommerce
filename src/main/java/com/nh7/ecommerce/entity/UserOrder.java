package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class UserOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id")
    private Long id;
    @Column(name = "order_price")
    private Double orderPrice;
    @Column(name = "status")
    private String orderStatus;
    @Column(name = "child_category")
    private String childCategory;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private List<Item> itemList=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
