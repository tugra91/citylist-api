package com.kuehnenagel.citylist.service.impl;

import com.kuehnenagel.citylist.common.constant.BusinessErrorEnum;
import com.kuehnenagel.citylist.common.exception.BusinessException;
import com.kuehnenagel.citylist.dao.UserRepository;
import com.kuehnenagel.citylist.dto.input.RegisterUserInput;
import com.kuehnenagel.citylist.dto.model.User;
import com.kuehnenagel.citylist.dto.output.RegisterUserOutput;
import com.kuehnenagel.citylist.entity.UserEntity;
import com.kuehnenagel.citylist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public RegisterUserOutput registerUser(RegisterUserInput input) {
        UserEntity existUser = userRepository.findByUsername(input.newUser().username()).orElse(null);
        if(ObjectUtils.isNotEmpty(existUser)) {
            throw new BusinessException(BusinessErrorEnum.EXIST_USER_RECORD_ERROR.getCode(), BusinessErrorEnum.EXIST_USER_RECORD_ERROR.getMessage());
        }
        User newUser = input.newUser();
        UserEntity newUserEntity = new UserEntity(null, newUser.username(), new BCryptPasswordEncoder().encode(newUser.password()), newUser.name(), "ALLOW_EDIT");
        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        return new RegisterUserOutput(User.convertUserEntityToUser(savedUserEntity));
    }
}
