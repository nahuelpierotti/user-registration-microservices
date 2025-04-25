package com.registrations.users.service;

import com.registrations.users.dto.UserRequestDto;
import com.registrations.users.model.User;

import java.util.List;

public interface IUserService {
    User save(UserRequestDto userRequestDto);
    User findById(long id);
    List<User> findAll();

}
