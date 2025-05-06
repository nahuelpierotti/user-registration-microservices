package com.registrations.users.service;

import com.registrations.users.config.MapperConfig;
import com.registrations.users.dto.UserLoggedResponseDto;
import com.registrations.users.dto.UserRequestDto;
import com.registrations.users.model.User;
import com.registrations.users.repo.IUserRepository;
import com.registrations.users.service.impl.UserService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTests {

    @Mock
    IUserRepository repository;

    MapperConfig mapper;

    UserService userService;

    private User userMock=null;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper=new MapperConfig();
        userService=new UserService(repository, mapper);

        userMock= User.builder()
                .idUser(Long.parseLong("2"))
                .name("John Doe")
                .email("john.doe@example.com")
                .password("a2asfGfdfdf4")
                .build();

    }

    @Test
    @DisplayName("Mapping Successfully a User to UserRequestDto")
    public void testMappingUserToUserRequestDto() {
        UserRequestDto userRequestDto=mapper.modelMapper().map(userMock, UserRequestDto.class);
        assertNotNull(userRequestDto);
    }

    @Test
    @DisplayName("Mapping Successfully a UserRequestDto to User")
    public void testMappingUserRequestDtoToUser() {
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setEmail("john.doe@example.com");
        userRequestDto.setPassword("a2asfGfdfdf4");
        User user=mapper.modelMapper().map(userRequestDto,User.class);
        assertNotNull(user);
    }

    @Test
    @DisplayName("Obtaining a user from email")
    public void obtainingUserFromEmail() {
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setEmail("john.doe@example.com");
        userRequestDto.setPassword("a2asfGfdfdf4");
        User user=mapper.modelMapper().map(userRequestDto,User.class);
        repository.save(user);
        when(repository.findOneByEmail("john.doe@example.com")).thenReturn(user);
        UserLoggedResponseDto userLogged=mapper.modelMapper().map(user,UserLoggedResponseDto.class);
        assertEquals("john.doe@example.com",userLogged.getEmail());
    }

    @Test
    @DisplayName("Saving a user")
    public void savingAUserFromUserRequestDto() {
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setEmail("john.doe@example.com");
        userRequestDto.setPassword("a2asfGfdfdf4");
        User user=userService.save(userRequestDto);
        assertNull(user);
    }
}
