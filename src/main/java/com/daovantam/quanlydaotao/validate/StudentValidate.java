package com.daovantam.quanlydaotao.validate;

import com.daovantam.quanlydaotao.exception.ValidateException;
import com.daovantam.quanlydaotao.model.request.student.StudentRequest;
import org.apache.commons.lang3.StringUtils;

public class StudentValidate {
    public static StudentRequest validate(StudentRequest studentRequest){
        return Validator.of(studentRequest, () -> new ValidateException("Object not null"))
                .validate(StudentRequest::getCode, StudentValidate::validCode, () -> new ValidateException("Code is valid"))
                .validate(StudentRequest::getEmail, StudentValidate::validEmail, () -> new ValidateException("Email is valid"))
                .validate(StudentRequest::getFullName, StudentValidate::validFullName, () -> new ValidateException("Full name is valid"))
                .validate(StudentRequest::getPassword, StudentValidate::validPassword, () -> new ValidateException("Password is valid"))
                .validate(StudentRequest::getAddress, StudentValidate::validAddress, () -> new ValidateException("Address is valid"))
                .validate(StudentRequest::getPhone, StudentValidate::validPhone, () -> new ValidateException("Phone number is valid"))
                .get();
    }

    private static boolean validCode(String code){
        return StringUtils.isBlank(code);
    }

    private static boolean validEmail(String email){
        return StringUtils.isBlank(email);
    }

    private static boolean validFullName(String fullName){
        return StringUtils.isBlank(fullName);
    }

    private static boolean validPassword(String password){
        return StringUtils.isBlank(password);
    }

    private static boolean validAddress(String address){
        return StringUtils.isBlank(address);
    }

    private static boolean validPhone(String phone){
        return StringUtils.isBlank(phone);
    }
}
