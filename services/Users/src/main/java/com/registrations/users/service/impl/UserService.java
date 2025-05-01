package com.registrations.users.service.impl;

import com.registrations.users.config.MapperConfig;
import com.registrations.users.config.PasswordEncoder;
import com.registrations.users.dto.PhoneDto;
import com.registrations.users.dto.UserLoggedResponseDto;
import com.registrations.users.dto.UserRequestDto;
import com.registrations.users.exception.UserAlreadyRegisteredException;
import com.registrations.users.model.User;
import com.registrations.users.repo.IUserRepository;
import com.registrations.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final MapperConfig modelMapper;


    @Override
    public User save(UserRequestDto userRequestDto) throws UserAlreadyRegisteredException{
        String encodedPassword = PasswordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(encodedPassword);
        User user = modelMapper.modelMapper().map(userRequestDto, User.class);
        try {
            return userRepository.save(user);
        }catch (Exception e){
            throw new UserAlreadyRegisteredException(e.getMessage());
        }
    }

    @Override
    public UserLoggedResponseDto findOneByEmail(String email) {
        User user= userRepository.findOneByEmail(email);
        UserLoggedResponseDto userLoggedResponseDto=modelMapper.modelMapper().map(user, UserLoggedResponseDto.class);
        List<PhoneDto> phoneDtoList=modelMapper.mapList(user.getPhones(), PhoneDto.class);
        userLoggedResponseDto.setUserPhoneList(phoneDtoList);
        return userLoggedResponseDto;
    }


}
