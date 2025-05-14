package com.example.customer.entity;

import com.example.customer.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Integer customerId;

    @Basic(optional = false)
    @Column(name = "customer_name")
    private String fullName;

    @Basic(optional = false)
    @Column(name = "user_name" ,unique = true)
    private String userName;

    @Basic(optional = false)
    @Column(name = "password", unique = true)
    private String password;

    @Basic(optional = false)
    @Column(name = "email", unique = true)
    private String email;

    @Basic(optional = false)
    @Column(name = "contact")
    private String contact;

    @Column(name = "city")
    private String city;

    @Basic(optional = false)
    @Column(name = "county")
    private String county;

    @Basic(optional = false)
    @Column(name = "address")
    private String address;

    @Column(name = "user_role")
    private Role role;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;
}
