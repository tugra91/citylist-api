package com.kuehnenagel.citylist.config;

import com.kuehnenagel.citylist.common.constant.ExceptionTypeEnum;
import com.kuehnenagel.citylist.common.dto.ExceptionOutput;
import com.kuehnenagel.citylist.common.exception.BusinessException;
import com.kuehnenagel.citylist.common.exception.RequestException;
import com.kuehnenagel.citylist.common.exception.SystemException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfiguration {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionOutput> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(new ExceptionOutput(ExceptionTypeEnum.BUSINESS_EXCEPTION.name(), ex.getMessage(), ex.getCode()), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ExceptionOutput> handleSystemException(SystemException ex) {
        return new ResponseEntity<>(new ExceptionOutput(ExceptionTypeEnum.SYSTEM_EXCEPTION.name(), ex.getMessage(), ex.getCode()), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ExceptionOutput> handleRequestException(RequestException ex) {
        return new ResponseEntity<>(new ExceptionOutput(ExceptionTypeEnum.REQUEST_EXCEPTION.name(), ex.getMessage(), ex.getCode()), new HttpHeaders(), HttpStatus.OK);
    }

}
