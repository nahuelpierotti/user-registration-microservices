package com.registrations.users.service;

import com.registrations.users.dto.UserLoggedResponseDto;
import com.registrations.users.dto.UserRequestDto;
import com.registrations.users.exception.UserAlreadyRegisteredException;
import com.registrations.users.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User save(UserRequestDto userRequestDto) throws UserAlreadyRegisteredException;
    UserLoggedResponseDto findOneByEmail(String email);
}
