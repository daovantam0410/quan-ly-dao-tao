package com.daovantam.quanlydaotao.mapper.user;

import com.daovantam.quanlydaotao.entity.Role;
import com.daovantam.quanlydaotao.entity.User;
import com.daovantam.quanlydaotao.model.request.user.UserChangePasswordRequest;
import com.daovantam.quanlydaotao.model.request.user.UserRegisterRequest;
import com.daovantam.quanlydaotao.model.request.user.UserRequest;
import com.daovantam.quanlydaotao.model.response.user.UserResponse;
import com.daovantam.quanlydaotao.repository.RoleRepository;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import com.daovantam.quanlydaotao.utils.TimeUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class UserMapper {
    private final RoleRepository roleRepository;

    public User mapToEntity(UserRequest userRequest){
        User user = new User();
        BeanUtils.refine(userRequest, user, BeanUtils::copyNonNull);
        user.setDateOfBirth(TimeUtil.convertToLocalDate(userRequest.getDateOfBirth()));

        List<Role> roles = roleRepository.findAll();

        for (Role role:roles) {
            if (role.getId() == userRequest.getRoleId()){
                user.setRole(role);
            }
        }

        return user;
    }

    public User mapToEntity(UserRegisterRequest registerRequest){
        User user = new User();
        BeanUtils.refine(registerRequest, user, BeanUtils::copyNonNull);
        user.setDateOfBirth(TimeUtil.convertToLocalDate(registerRequest.getDateOfBirth()));

        return user;
    }

    public User mapToEntity(UserChangePasswordRequest request){
        User user = new User();
        BeanUtils.refine(request, user, BeanUtils::copyNonNull);

        return user;
    }

    public UserResponse mapToResponse(User user){
        UserResponse userResponse = new UserResponse();
        BeanUtils.refine(user, userResponse, BeanUtils::copyNonNull);
        userResponse.setDateOfBirth(TimeUtil.convertToString(user.getDateOfBirth()));

        List<Role> roles = roleRepository.findAll();

        for (Role role:roles) {
            if (role.getId() == user.getRole().getId()){
                userResponse.setRoleId(role.getId());
            }
        }

        return userResponse;
    }
}
