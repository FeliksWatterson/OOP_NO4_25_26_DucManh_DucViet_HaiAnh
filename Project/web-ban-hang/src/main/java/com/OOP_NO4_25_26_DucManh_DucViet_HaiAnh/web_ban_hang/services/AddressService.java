package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.AddressInterface;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Address;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements AddressInterface {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> findAddressesByCustomer(Customer customer) {
        return addressRepository.findByCustomer(customer);
    }

    @Override
    public Optional<Address> findDefaultAddressByCustomer(Customer customer) {
        return addressRepository.findByCustomerAndIsDefaultTrue(customer);
    }

    @Override
    @Transactional
    public Address saveAddress(Address address) {
        if (address.isDefault()) {
            Optional<Address> currentDefault = findDefaultAddressByCustomer(address.getCustomer());
            currentDefault.ifPresent(def -> {
                if (!def.getId().equals(address.getId())) { 
                    def.setDefault(false);
                    addressRepository.save(def);
                }
            });
        } else {
            long addressCount = countAddressesByCustomer(address.getCustomer());
            if (address.getId() == null && addressCount == 0) {
                 address.setDefault(true);
            } else if (address.getId() != null && !findDefaultAddressByCustomer(address.getCustomer()).isPresent()) {
                 List<Address> addresses = findAddressesByCustomer(address.getCustomer());
                 if (addresses.size() == 1 && addresses.get(0).getId().equals(address.getId())) {
                      address.setDefault(true);
                 }
            }
        }
        return addressRepository.save(address);
    }


    @Override
    public Optional<Address> findByIdAndCustomer(Long id, Customer customer) {
        Optional<Address> addressOpt = addressRepository.findById(id);
        if (addressOpt.isPresent() && addressOpt.get().getCustomer().getCustomerId().equals(customer.getCustomerId())) {
            return addressOpt;
        }
        return Optional.empty();
    }

     @Override
    @Transactional
    public void deleteAddress(Long id, Customer customer) {
        Optional<Address> addressOpt = findByIdAndCustomer(id, customer);
        if (addressOpt.isPresent()) {
            Address addressToDelete = addressOpt.get();
            boolean wasDefault = addressToDelete.isDefault();
            addressRepository.delete(addressToDelete);

            if (wasDefault) {
                List<Address> remainingAddresses = findAddressesByCustomer(customer);
                if (!remainingAddresses.isEmpty()) {
                    Address newDefault = remainingAddresses.get(0);
                    newDefault.setDefault(true);
                    addressRepository.save(newDefault);
                }
            }
        } else {
            throw new RuntimeException("Không tìm thấy địa chỉ hoặc bạn không có quyền xóa.");
        }
    }


    @Override
    @Transactional
    public void setDefaultAddress(Long id, Customer customer) {
        Optional<Address> currentDefaultOpt = findDefaultAddressByCustomer(customer);
        currentDefaultOpt.ifPresent(currentDefault -> {
            currentDefault.setDefault(false);
            addressRepository.save(currentDefault);
        });

        Optional<Address> newDefaultOpt = findByIdAndCustomer(id, customer);
        if (newDefaultOpt.isPresent()) {
            Address newDefault = newDefaultOpt.get();
            newDefault.setDefault(true);
            addressRepository.save(newDefault);
        } else {
            throw new RuntimeException("Không tìm thấy địa chỉ hoặc bạn không có quyền.");
        }
    }


    @Override
    public long countAddressesByCustomer(Customer customer) {
        return addressRepository.countByCustomer(customer);
    }
}