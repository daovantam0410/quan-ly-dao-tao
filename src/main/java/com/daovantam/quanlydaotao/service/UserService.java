package com.daovantam.quanlydaotao.service;

import com.daovantam.quanlydaotao.entity.User;
import com.daovantam.quanlydaotao.model.request.user.UserChangePasswordRequest;
import com.daovantam.quanlydaotao.model.request.user.UserRegisterRequest;
import com.daovantam.quanlydaotao.model.request.user.UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public interface UserService extends BaseService<User, Long>{
    User register(UserRegisterRequest userRequest, Function<UserRegisterRequest, User> transform);

    User changePassword(UserChangePasswordRequest request, Function<UserChangePasswordRequest, User> transform);
}
