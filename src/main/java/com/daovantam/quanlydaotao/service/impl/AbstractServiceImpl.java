package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.exception.ObjectNotFoundException;
import com.daovantam.quanlydaotao.model.response.PageResponse;
import com.daovantam.quanlydaotao.repository.BaseRepository;
import com.daovantam.quanlydaotao.service.BaseService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public abstract class AbstractServiceImpl<T, ID> implements BaseService<T, ID> {

    protected final BaseRepository baseRepository;

    public AbstractServiceImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public <RP> PageResponse<RP> getAll(Pageable pageable, Function<T, RP> transform){
        Page<T> page = baseRepository.findAll(pageable.previousOrFirst());
        List<RP> data = page.stream().map(transform::apply).collect(Collectors.toList());

        return PageResponse.of(page.getTotalElements(), data);
    }

    @Override
    public <RP> RP findById(ID id, Function<T, RP> transform){
        Optional<T> t = baseRepository.findById(id);
        t.orElseThrow(() -> new ObjectNotFoundException("Not found with id: " + id));

        return transform.apply(t.get());
    }

}
