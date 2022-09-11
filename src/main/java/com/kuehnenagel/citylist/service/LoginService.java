package com.kuehnenagel.citylist.service;

import com.kuehnenagel.citylist.dto.input.SignInInput;
import com.kuehnenagel.citylist.dto.output.SignInOutput;

public interface LoginService {

    SignInOutput signIn (SignInInput input);
}
