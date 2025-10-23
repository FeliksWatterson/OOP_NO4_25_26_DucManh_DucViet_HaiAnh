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
        Customer customer = address.getCustomer();
        Long currentAddressId = address.getId(); 

        if (address.isDefault()) {
            List<Address> allAddresses = findAddressesByCustomer(customer);
            for (Address existingAddress : allAddresses) {
                if (existingAddress.isDefault() && (currentAddressId == null || !existingAddress.getId().equals(currentAddressId))) {
                    existingAddress.setDefault(false);
                    addressRepository.save(existingAddress);
                }
            }
            address.setDefault(true);
        }
        else {
             address.setDefault(false); 
        }

        Address savedAddress = addressRepository.save(address);
        long addressCountAfterSave = countAddressesByCustomer(customer);
        if (addressCountAfterSave > 0) { 
            Optional<Address> checkDefaultExists = addressRepository.findByCustomerAndIsDefaultTrue(customer);
            if (checkDefaultExists.isEmpty()) {
                List<Address> remainingAddresses = findAddressesByCustomer(customer);
                if (!remainingAddresses.isEmpty()) {
                    Address addressToMakeDefault = null;
                    for(Address addr : remainingAddresses) {
                        if (addr.getId().equals(savedAddress.getId())) {
                            addressToMakeDefault = addr;
                            break;
                        }
                    }
                    if (addressToMakeDefault == null) {
                        addressToMakeDefault = remainingAddresses.get(0);
                    }

                    addressToMakeDefault.setDefault(true);
                    addressRepository.save(addressToMakeDefault);
                    if (savedAddress.getId().equals(addressToMakeDefault.getId())) {
                        savedAddress.setDefault(true);
                    }
                }
            }
        }


        return savedAddress; 
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
    Optional<Address> newDefaultOpt = findByIdAndCustomer(id, customer);
    if (newDefaultOpt.isEmpty()) {
        throw new RuntimeException("Không tìm thấy địa chỉ hoặc bạn không có quyền.");
    }
    Address newDefaultAddress = newDefaultOpt.get();
    List<Address> allAddresses = findAddressesByCustomer(customer);

    for (Address address : allAddresses) {
        if (!address.getId().equals(newDefaultAddress.getId()) && address.isDefault()) {
            address.setDefault(false);
            addressRepository.save(address); 
        }
        else if (address.getId().equals(newDefaultAddress.getId()) && !address.isDefault()) {
            address.setDefault(true);
            addressRepository.save(address); 
        }
    
    }

    if (!newDefaultAddress.isDefault()) {
         newDefaultAddress.setDefault(true);
         addressRepository.save(newDefaultAddress);
    }
}
    @Override
    public long countAddressesByCustomer(Customer customer) {
        return addressRepository.countByCustomer(customer);
    }
}