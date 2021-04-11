package com.nh7.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;

@Entity
@Table(name="user_details")
@Getter
@Setter
public class UserDetails extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String gmail;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String fullName;

    @Column
    private String dayOfBird;

    @Column
    private String gender;



}
