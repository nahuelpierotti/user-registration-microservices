package com.registrations.users.controller;

import com.registrations.users.dto.UserLoggedResponseDto;
import com.registrations.users.dto.UserRequestDto;
import com.registrations.users.dto.UserResponseDto;
import com.registrations.users.model.User;

import com.registrations.users.service.IUserService;
import com.registrations.users.service.impl.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto userRequestDto) throws Exception {
        User user = userService.save(userRequestDto);
        jwtService.generateToken(user);
        UserResponseDto userWithToken = modelMapper.map(user, UserResponseDto.class);

        return ResponseEntity.ok(userWithToken);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoggedResponseDto> login(HttpServletRequest request)  {
        String header = request.getHeader("Authorization");
        String token=null;
            if(header!=null && header.startsWith("Bearer ")) {
                token = header.substring(7);
            }
        String userEmail= jwtService.getUsernameFromToken(token);
        UserLoggedResponseDto user=userService.findOneByEmail(userEmail);

        return ResponseEntity.ok(user);
    }
}
