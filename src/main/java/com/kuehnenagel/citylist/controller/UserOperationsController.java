package com.kuehnenagel.citylist.controller;

import com.kuehnenagel.citylist.dto.input.RegisterUserInput;
import com.kuehnenagel.citylist.dto.input.SignInInput;
import com.kuehnenagel.citylist.dto.output.RegisterUserOutput;
import com.kuehnenagel.citylist.dto.output.SignInOutput;

public interface UserOperationsController {

    RegisterUserOutput registerUser(RegisterUserInput input);

    SignInOutput signIn(SignInInput input);
}
