package com.airbnb1.controller;

import com.airbnb1.entity.PropertyUser;
import com.airbnb1.payload.LoginDto;
import com.airbnb1.payload.PropertyUserDto;
import com.airbnb1.service.PropertyUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private PropertyUserService propertyUserService;

    public UserController(PropertyUserService propertyUserService) {
        this.propertyUserService = propertyUserService;
    }

    @PostMapping("/adduser")
    public ResponseEntity<?>addUser(@RequestBody PropertyUserDto propertyUserDto)
    {
        PropertyUser user = propertyUserService.createUser(propertyUserDto);
        if (user!=null)
        {
            return new ResponseEntity<>("Registration Successful", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?>loginUser(@RequestBody LoginDto loginDto)
    {
        String token = propertyUserService.loginUser(loginDto);
        if (token!=null)
        {
            return  new ResponseEntity<>(token,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
    }
}
