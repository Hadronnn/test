package com.oaec.shoes.service.impl;

import com.oaec.shoes.dao.AddressDao;
import com.oaec.shoes.dao.impl.AddressDaoImpl;
import com.oaec.shoes.entil.Address;
import com.oaec.shoes.service.AddressService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();
    @Override
    public Boolean addAddress(Address address) {
        int i = addressDao.doInsert(address);
        return i==1;
    }
    @Override
    public List<Address> queryByUserId(Integer userId) {
        List<Address> list = addressDao.queryByUserId(userId);
        return list;
    }
    @Override
    public Address queryByAddressId(Integer addressId) {
        return addressDao.queryByAddressId(addressId);
    }
    @Override
    public Boolean delete(Integer addressId) {
        return addressDao.updateState(addressId) == 1;
    }
}
