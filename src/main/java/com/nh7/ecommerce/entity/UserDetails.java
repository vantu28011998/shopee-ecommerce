package com.nh7.ecommerce.entity;

import com.nh7.ecommerce.enums.AuthProviderEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="user_details")
@Getter
@Setter
public class UserDetails extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

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
