package com.example.transactional.service;

import com.example.transactional.entity.Person_parent;
import com.example.transactional.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonLogService childService;

    // Person : 부모 , PersonLog : 자식
    @Transactional
    public void parentMethod_1(Long id, int age, String name) {
        Person_parent person_parent = personRepository.findById(id).orElseThrow(RuntimeException::new);
        person_parent.setAge(age);
        try {
            childService.childMethod_REQUIRES(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        person_parent.setName(name);
    }

    @Transactional
    public void parentMethod_2(Long id, int age, String name) {
        Person_parent person_parent = personRepository.findById(id).orElseThrow(RuntimeException::new);
        person_parent.setAge(age);
        try {
            childService.childMethod_REQUIRESNEW(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        person_parent.setName(name);
    }


}
