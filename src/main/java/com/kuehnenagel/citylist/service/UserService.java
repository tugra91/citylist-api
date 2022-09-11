package com.kuehnenagel.citylist.service;

import com.kuehnenagel.citylist.dto.input.RegisterUserInput;
import com.kuehnenagel.citylist.dto.output.RegisterUserOutput;

public interface UserService {

    RegisterUserOutput registerUser(RegisterUserInput input);
}
