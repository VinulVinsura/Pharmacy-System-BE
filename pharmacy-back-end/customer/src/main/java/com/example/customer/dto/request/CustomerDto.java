package com.example.customer.dto.request;

import com.example.customer.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Integer customerId;
    private String fullName;
    private String userName;
    private String password;
    private String email;
    private String contact;
    private String city;
    private String county;
    private String address;
    private Role role;
    private Date createdTime;
    private Date updatedTime;
}
