package com.kuehnenagel.citylist.service;


import com.kuehnenagel.citylist.common.exception.BusinessException;
import com.kuehnenagel.citylist.dao.UserRepository;
import com.kuehnenagel.citylist.dto.input.RegisterUserInput;
import com.kuehnenagel.citylist.dto.model.User;
import com.kuehnenagel.citylist.dto.output.RegisterUserOutput;
import com.kuehnenagel.citylist.entity.UserEntity;
import com.kuehnenagel.citylist.service.impl.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith({MockitoExtension.class})
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void init() {
        userService = new IUserService(userRepository);
    }

    @Test
    public void testRegisterUser_validRequest_returnSuccess() {

        RegisterUserInput input = new RegisterUserInput(new User("admin", "admin", "admin"));
        Mockito.when(userRepository.findByUsername(Mockito.refEq(input.newUser().username()))).thenReturn(Optional.empty());
        RegisterUserOutput actualOutput = userService.registerUser(input);

        Assertions.assertNotNull(actualOutput.user());
        Assertions.assertEquals(input.newUser().username(), actualOutput.user().username());
    }

    @Test
    public void testRegisterUser_validRequestExistUser_returnSuccess() {
        RegisterUserInput input = new RegisterUserInput(new User("admin", "admin", "admin"));
        Mockito.when(userRepository.findByUsername(Mockito.refEq(input.newUser().username()))).thenReturn(Optional.of(new UserEntity("admin", "admin", "admin", "admin", "admin")));
        Assertions.assertThrows(BusinessException.class, () -> userService.registerUser(input));
    }

}
