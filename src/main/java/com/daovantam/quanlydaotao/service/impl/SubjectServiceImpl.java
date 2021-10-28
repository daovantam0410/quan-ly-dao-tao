package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.entity.Subject;
import com.daovantam.quanlydaotao.repository.SubjectRepository;
import com.daovantam.quanlydaotao.service.SubjectService;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends AbstractServiceImpl<Subject, Long> implements SubjectService {

    public SubjectServiceImpl(SubjectRepository subjectRepository){
        super(subjectRepository);
    }
}
