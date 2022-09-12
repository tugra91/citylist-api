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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@ExtendWith({MockitoExtension.class})
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        userService = new IUserService(userRepository, passwordEncoder);
    }

    @Test
    public void testRegisterUser_validRequest_returnSuccess() {

        RegisterUserInput input = new RegisterUserInput(new User("admin", "admin", "admin"));
        UserEntity savedEntity = new UserEntity(null,"admin", "admin", "admin", "ALLOW_EDIT");
        Mockito.when(userRepository.findByUsername(Mockito.refEq(input.newUser().username()))).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("admin")).thenReturn("admin");
        Mockito.when(userRepository.save(Mockito.refEq(savedEntity))).thenReturn(savedEntity);
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
