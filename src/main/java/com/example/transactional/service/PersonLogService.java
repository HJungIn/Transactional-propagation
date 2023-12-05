package com.example.transactional.service;

import com.example.transactional.entity.PersonLog_child;
import com.example.transactional.repository.PersonLogRepository;
import com.example.transactional.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonLogService {

    private final PersonLogRepository personLogRepository;


    @Transactional
    public void childMethod_REQUIRES(Long personId) {
        PersonLog_child log = PersonLog_child.builder().personId(personId).build();
        personLogRepository.save(log);
        throw new RuntimeException("unexpected exception 발생");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void childMethod_REQUIRESNEW(Long personId) {
        PersonLog_child log = PersonLog_child.builder().personId(personId).build();
        personLogRepository.save(log);
        throw new RuntimeException("unexpected exception 발생");
    }
}
