package com.daovantam.quanlydaotao.controller.user;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.entity.User;
import com.daovantam.quanlydaotao.exception.MessageResponse;
import com.daovantam.quanlydaotao.mapper.user.UserMapper;
import com.daovantam.quanlydaotao.model.request.user.UserAuthRequest;
import com.daovantam.quanlydaotao.model.request.user.UserChangePasswordRequest;
import com.daovantam.quanlydaotao.model.request.user.UserRegisterRequest;
import com.daovantam.quanlydaotao.model.request.user.UserRequest;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import com.daovantam.quanlydaotao.model.response.user.UserResponse;
import com.daovantam.quanlydaotao.repository.specification.UserSpecification;
import com.daovantam.quanlydaotao.security.jwt.TokenProducer;
import com.daovantam.quanlydaotao.security.jwt.model.JwtPayload;
import com.daovantam.quanlydaotao.service.UserService;
import com.daovantam.quanlydaotao.utils.PasswordHasher;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/admin/manager-user")
@RestController
@RequestMapping("/admin")
@Data
public class UserController extends BaseController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenProducer tokenProducer;

    private JwtPayload createPayload(User user){
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setId(user.getId());
        jwtPayload.setUserName(user.getUserName());
        jwtPayload.setFullName(user.getFullName());
        jwtPayload.setEmail(user.getEmail());
        jwtPayload.setRole(user.getRole().getName());

        return jwtPayload;
    }

    @GetMapping("/users")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll(userMapper::mapToResponse));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(userService.findById(id, userMapper::mapToResponse));
    }

    @PostMapping("/user")
    public ResponseEntity<Void> insert(@RequestBody UserRequest userRequest){
        String password = PasswordHasher.hash(userRequest.getPassword());
        userRequest.setPassword(password);
        userService.save(userRequest, userMapper::mapToEntity);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Void> update(@RequestBody UserRequest userRequest, @PathVariable long id){
        userRequest.setId(id);
        userService.save(userRequest, userMapper::mapToEntity);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<BaseResponse> auth(@RequestBody UserAuthRequest authRequest){
        String password = PasswordHasher.hash(authRequest.getPassword());
        authRequest.setPassword(password);

        User user = userService.findOne(UserSpecification.authFilter(authRequest));
        JwtPayload jwtPayload = createPayload(user);

        return success(tokenProducer.token(jwtPayload));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> register(@RequestBody UserRegisterRequest registerRequest){
        userService.register(registerRequest, userMapper::mapToEntity);
        return ResponseEntity.ok(new MessageResponse("Register successfully"));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<BaseResponse> changePassword(@RequestBody UserChangePasswordRequest changePassword, @PathVariable long id){
        changePassword.setId(id);
        userService.changePassword(changePassword, userMapper::mapToEntity);
        return success();
    }

}
