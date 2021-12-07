package com.daovantam.quanlydaotao.repository.specification;

import com.daovantam.quanlydaotao.entity.Branch;
import com.daovantam.quanlydaotao.model.request.branch.BranchFilterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class BranchSpecification {
    public static Specification<Branch> filter(BranchFilterRequest request){
        return Specification.where(withCode(request.getCode())).and(withName(request.getName()));
    }

    private static Specification<Branch> withCode(String code){
        if (StringUtils.isBlank(code)) return null;

        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("code"), code));
    }

    private static Specification<Branch> withName(String name){
        if (StringUtils.isBlank(name)) return null;

        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name));
    }
}
