package com.dprsnn.UtilPlast.services;

import com.dprsnn.UtilPlast.models.PointAddress;
import com.dprsnn.UtilPlast.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<PointAddress> adressList(){
        return addressRepository.findAll();
    }

    public void addAddress(String newAdress) {
        PointAddress address = new PointAddress();
        address.setAddress(newAdress);
        addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public PointAddress getAddressById(Long id) {
        return addressRepository.getPointAddressById(id);
    }

    public void saveAddress(Long id, String address) {
        PointAddress pointAddress = addressRepository.getPointAddressById(id);
        pointAddress.setAddress(address);

        addressRepository.save(pointAddress);
    }
}
