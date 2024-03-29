package com.airbnb1.service;

import com.airbnb1.entity.PropertyUser;
import com.airbnb1.payload.LoginDto;
import com.airbnb1.payload.PropertyUserDto;

public interface PropertyUserService {
    PropertyUser createUser(PropertyUserDto propertyUserDto);

    String loginUser(LoginDto loginDto);
}
