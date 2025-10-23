package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers") 
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id") 
    private Long customerId; 

    @Column(name = "full_name", nullable = false, length = 100) 
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100) 
    private String email;

    @Column(name = "phone", length = 20) 
    private String phone;

    @Column(name = "password", nullable = false, length = 255) 
    private String password;

    public Customer() {
    }

    public Customer(String fullName, String email, String phone, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password; 
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
