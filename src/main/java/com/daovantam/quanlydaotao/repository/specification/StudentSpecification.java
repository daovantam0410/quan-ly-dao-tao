package com.daovantam.quanlydaotao.repository.specification;

import com.daovantam.quanlydaotao.entity.Student;
import com.daovantam.quanlydaotao.model.request.student.StudentAuthRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    private static Specification<Student> withCode(String code){
        if (StringUtils.isBlank(code)) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("code"), code);
    }

    private static Specification<Student> withPassword(String password){
        if (StringUtils.isBlank(password)) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("password"), password);
    }

    public static Specification<Student> authFilter(StudentAuthRequest studentAuthRequest){
        return Specification.where(withCode(studentAuthRequest.getCode())).and(withPassword(studentAuthRequest.getPassword()));
    }
}
