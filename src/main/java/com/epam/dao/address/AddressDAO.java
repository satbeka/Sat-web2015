package com.epam.dao.address;


import com.epam.model.Address;

import javax.sql.RowSet;
import java.util.List;

public interface AddressDAO {

    public int insertAddress(Address address);
    public boolean deleteAddress(Address address);
    public Address findAddressByName(String name);
    public Address findAddressById(long id);
    public boolean updateAddress(Address address);
    public RowSet selectAddressRS(Address address);
    public List<Address> selectAddressTO();

}
