package com.daovantam.quanlydaotao.service;

import com.daovantam.quanlydaotao.model.response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Function;

public interface BaseService<T, ID> {
    <RP> PageResponse<RP> getAll(Pageable pageable, Function<T, RP> transform);

    <RP>List<RP> findAll(Function<T, RP> transform);

    <RP> RP findById(ID id, Function<T, RP> transform);

    <RQ> void save(RQ req, Function<RQ, T> transform);

//    <RQ> void save(RQ req, Function<RQ, T> transform, MultipartFile multipartFile);

    void delete(ID id);

    T findOne(Specification<T> filter);

    <RP> RP findOne(Specification<T> filter, Function<T, RP> transform);

    <RP> List<RP> filter(Specification<T> filter, Function<T, RP> transform);

}
