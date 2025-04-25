package com.registrations.users.service.impl;

import com.registrations.users.config.MapperConfig;
import com.registrations.users.dto.UserRequestDto;
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
    public User save(UserRequestDto userRequestDto) {
        User user = modelMapper.modelMapper().map(userRequestDto, User.class);
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return List.of((User) userRepository.findAll());
    }
}
