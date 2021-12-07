package com.daovantam.quanlydaotao.repository.specification;

import com.daovantam.quanlydaotao.entity.User;
import com.daovantam.quanlydaotao.model.request.user.UserAuthRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    private static Specification<User> withUserName(String userName){
        if (StringUtils.isBlank(userName)) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userName"), userName);
    }

    private static Specification<User> withPassword(String password){
        if (StringUtils.isBlank(password)) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("password"), password);
    }

    public static Specification<User> authFilter(UserAuthRequest authRequest){
        return Specification.where(withUserName(authRequest.getUserName())).and(withPassword(authRequest.getPassword()));
    }
}
