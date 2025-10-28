package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String phone;
    private String city;
    private String district;
    private String ward;
    private String addressDetail; 
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }

    public String getAddressDetail() { return addressDetail; }
    public void setAddressDetail(String addressDetail) { this.addressDetail = addressDetail; }

    public boolean isDefault() { return isDefault; }
    public void setDefault(boolean aDefault) { isDefault = aDefault; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public String getFullShippingAddress() {
        List<String> parts = new ArrayList<>();
        if (addressDetail != null && !addressDetail.trim().isEmpty()) {
            parts.add(addressDetail.trim());
        }
        if (ward != null && !ward.trim().isEmpty()) {
            parts.add(ward.trim());
        }
        if (district != null && !district.trim().isEmpty()) {
            parts.add(district.trim());
        }
        if (city != null && !city.trim().isEmpty()) {
            parts.add(city.trim());
        }
        return parts.stream().filter(part -> part != null && !part.isEmpty())
                   .collect(Collectors.joining(", "));
    }
}
