package com.kuehnenagel.citylist.controller.impl;

import com.kuehnenagel.citylist.controller.UserOperationsController;
import com.kuehnenagel.citylist.dto.input.RegisterUserInput;
import com.kuehnenagel.citylist.dto.input.SignInInput;
import com.kuehnenagel.citylist.dto.output.RegisterUserOutput;
import com.kuehnenagel.citylist.dto.output.SignInOutput;
import com.kuehnenagel.citylist.service.LoginService;
import com.kuehnenagel.citylist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class IUserOperationsController implements UserOperationsController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping(value = "/registerUser")
    @Override
    public RegisterUserOutput registerUser(@RequestBody RegisterUserInput input) {
        return userService.registerUser(input);
    }

    @PostMapping(value = "/signIn")
    @Override
    public SignInOutput signIn(@RequestBody SignInInput input) {
        return loginService.signIn(input);
    }
}
