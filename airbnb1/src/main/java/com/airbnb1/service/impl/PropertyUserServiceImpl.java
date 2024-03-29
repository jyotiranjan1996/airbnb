package com.airbnb1.service.impl;

import com.airbnb1.entity.PropertyUser;
import com.airbnb1.exception.UsernameEmailAlreadyExistException;
import com.airbnb1.payload.LoginDto;
import com.airbnb1.payload.PropertyUserDto;
import com.airbnb1.repository.PropertyUserRepository;
import com.airbnb1.service.PropertyUserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PropertyUserServiceImpl implements PropertyUserService {

    private PropertyUserRepository propertyUserRepository;
    private JWTService jwtService;

    public PropertyUserServiceImpl(PropertyUserRepository propertyUserRepository, JWTService jwtService) {
        this.propertyUserRepository = propertyUserRepository;
        this.jwtService = jwtService;
    }

    @Override
    public PropertyUser createUser(PropertyUserDto propertyUserDto) {
        PropertyUser user = new PropertyUser();

        if (propertyUserRepository.existsByEmail(propertyUserDto.getEmail()))
        {
            throw new UsernameEmailAlreadyExistException("Email Is Already Registered");
        }
        if (propertyUserRepository.existsByUsername(propertyUserDto.getUsername()))
        {
            throw new UsernameEmailAlreadyExistException("Username Is Already Registered");
        }

        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setEmail(propertyUserDto.getEmail());
        user.setUsername(propertyUserDto.getUsername());
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt(10)));
        user.setUserRole(propertyUserDto.getUserRole());

        return propertyUserRepository.save(user);

    }

    @Override
    public String loginUser(LoginDto loginDto) {
        PropertyUser propertyUser = propertyUserRepository.findByUsername(loginDto.getUsername()).orElseThrow(
                () -> new UsernameEmailAlreadyExistException("Username Is Incorrect"));
        if (propertyUser!=null)
        {
            if (BCrypt.checkpw(propertyUser.getPassword(),loginDto.getPassword()))
            {
                return jwtService.generateToken(propertyUser);
            }
        }
        return null;
    }
}
