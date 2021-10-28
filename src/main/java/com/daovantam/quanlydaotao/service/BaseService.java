package com.daovantam.quanlydaotao.service;

import com.daovantam.quanlydaotao.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.function.Function;

public interface BaseService<T, ID> {
    <RP> PageResponse<RP> getAll(Pageable pageable, Function<T, RP> transform);

    <RP> RP findById(ID id, Function<T, RP> transform);
}
