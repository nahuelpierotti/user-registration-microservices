package com.registrations.users.service;

import com.registrations.users.dto.UserRequestDto;
import com.registrations.users.model.Phone;
import com.registrations.users.model.User;
import com.registrations.users.service.impl.UserService;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceTests {

    private UserService userService;
    private User userMock=null;
    private UserRequestDto userRequestDtoMock=null;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    }


    @BeforeEach
    public void setUp() {
        userMock= User.builder()
                .idUser(Long.parseLong("2"))
                .name("John Doe")
                .email("john.doe@example.com")
                .password("a2asfGfdfdf4")
                .build();

        Phone phone= Phone.builder()
                .idPhone(Long.parseLong("1"))
                .number(Long.parseLong("1150554949"))
                .citycode(1)
                .countrycode("54")
                .user(userMock)
                .build();
        ModelMapper modelMapper = new ModelMapper();

        userRequestDtoMock=modelMapper.map(userMock, UserRequestDto.class);
        userService.save(userRequestDtoMock);
    }


    @Test
    @DisplayName("Obtaining a user from email")
    public void save2() {
        //Assertions.assertEquals(userService.findOneByEmail("john.doe@example.com").getEmail(),userRequestDtoMock.getEmail());

    }


}
