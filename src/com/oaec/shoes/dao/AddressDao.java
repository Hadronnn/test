package com.oaec.shoes.dao;

import com.oaec.shoes.entil.Address;

import java.util.List;

public interface AddressDao {
    int doInsert(Address address);
    List<Address> queryByUserId(Integer userId);
    int updateState(Integer addressId);
    Address queryByAddressId(Integer addressId);
}
