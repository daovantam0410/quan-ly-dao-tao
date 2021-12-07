package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.entity.Role;
import com.daovantam.quanlydaotao.entity.User;
import com.daovantam.quanlydaotao.constant.Authorities;
import com.daovantam.quanlydaotao.exception.ObjectNotFoundException;
import com.daovantam.quanlydaotao.mapper.user.UserMapper;
import com.daovantam.quanlydaotao.model.request.user.UserChangePasswordRequest;
import com.daovantam.quanlydaotao.model.request.user.UserRegisterRequest;
import com.daovantam.quanlydaotao.repository.RoleRepository;
import com.daovantam.quanlydaotao.repository.UserRepository;
import com.daovantam.quanlydaotao.service.UserService;
import com.daovantam.quanlydaotao.utils.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User register(UserRegisterRequest userRequest, Function<UserRegisterRequest, User> transform) {
        User user = transform.apply(userRequest);
        throwIfExistUserName(userRequest.getUserName());
        throwIfExistEmail(userRequest.getEmail());
        String password = PasswordHasher.hash(userRequest.getPassword());
        user.setPassword(password);

        Long roleId = userRequest.getRoleId();

        if (roleId == null){
            Role role = roleRepository.findById(Authorities.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Role is not found"));
            user.setRole(role);
        }else{
            Role role = roleRepository.findById(Authorities.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Role is not found"));
            user.setRole(role);
        }
        return userRepository.save(user);
    }

    @Override
    public User changePassword(UserChangePasswordRequest request, Function<UserChangePasswordRequest, User> transform) {
        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new ObjectNotFoundException("Can not find " + request.getUserName()));
        boolean passwordMatch = PasswordHasher.hash(request.getOldPassword()).equals(user.getPassword());
        if (passwordMatch){
            String password = PasswordHasher.hash(request.getNewPassword());
            user.setPassword(password);
        }
        return userRepository.save(user);
    }

    private void throwIfExistUserName(String userName){
        Optional<User> existUser = userRepository.findByUserName(userName);
        existUser.ifPresent((user) -> {
            throw new IllegalArgumentException("Username is already taken!");
        });
    }

    private void throwIfExistEmail(String email){
        Optional<User> existEmail = userRepository.findByEmail(email);
        existEmail.ifPresent((user) -> {
            throw new IllegalArgumentException("Email is already in use!");
        });
    }

}
