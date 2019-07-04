package com.oaec.shoes.dao.impl;

import com.oaec.shoes.dao.AddressDao;
import com.oaec.shoes.entil.Address;
import com.oaec.shoes.util.CommonDao;

import java.util.List;

public class AddressDaoImpl extends CommonDao implements AddressDao {
    @Override
    public int doInsert(Address address) {
        String sql = "INSERT INTO adress VALUES(seq_adress.nextval,?,?,?,?,0,0,sysdate,?,?,?,?)";
        return executeUpdate(sql,address.getName(),address.getContact(),address.getAdress(),address.getUserId(),address.getProvince(),address.getCity(),address.getCounty(),address.getCode());
    }

    @Override
    public List<Address> queryByUserId(Integer userId) {
        String sql = "SELECT * FROM adress WHERE USER_ID = ? AND STATE = 0";
        return query4BeanList(sql,Address.class,userId);
    }

    @Override
    public int updateState(Integer addressId) {
        String sql = "UPDATE adress SET STATE = 1 WHERE ADRESS_ID = ?";
        return executeUpdate(sql,addressId);
    }
    @Override
    public Address queryByAddressId(Integer addressId) {
        String sql = "SELECT * FROM adress WHERE ADRESS_ID = ?";
        return query4Bean(sql,Address.class,addressId);
    }
}
