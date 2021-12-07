package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.exception.ObjectNotFoundException;
import com.daovantam.quanlydaotao.model.response.PageResponse;
import com.daovantam.quanlydaotao.repository.BaseRepository;
import com.daovantam.quanlydaotao.service.BaseService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

    public <RP> List<RP> findAll(Function<T, RP> transform){
        List<T> list = baseRepository.findAll();
        List<RP> data = list.stream().map(transform::apply).collect(Collectors.toList());

        return data;
    }

    @Override
    public <RP> RP findById(ID id, Function<T, RP> transform){
        Optional<T> t = baseRepository.findById(id);
        t.orElseThrow(() -> new ObjectNotFoundException("Not found with id: " + id));

        return transform.apply(t.get());
    }

    @Override
    public <RQ> void save(RQ req, Function<RQ, T> transform){
        T t = transform.apply(req);
        baseRepository.save(t);
    }

    /*public <RQ> void save(RQ req, Function<RQ, T> transform, MultipartFile multipartFile){
        if (!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        }
        T t = transform.apply(req);
        baseRepository.save(t);
    }*/

    @Override
    public void delete(ID id){
        baseRepository.deleteById(id);
    }

    public T findOne(Specification<T> filter){
        Optional<T> t = baseRepository.findOne(filter);
        t.orElseThrow(() -> new ObjectNotFoundException("Not found with condition"));

        return t.get();
    }

    public <RP> RP findOne(Specification<T> filter, Function<T, RP> transform){
        Optional<T> t = baseRepository.findById(filter);
        t.orElseThrow(() -> new ObjectNotFoundException("Not found with condition"));

        return transform.apply(t.get());
    }

    public <RP> List<RP> filter(Specification<T> filter, Function<T, RP> transform) {
        List<T> list = baseRepository.findAll(filter);
        return list.stream().map(transform::apply).collect(Collectors.toList());
    }
}
