package com.example.transactional.service;

import com.example.transactional.entity.Person_parent;
import com.example.transactional.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;


    @Test
    @DisplayName("[REQUIRED]자식메소드에서 예외가 일어날 때, 부모 메소드도 같이 rollback 된다.")
    public void REQUIRED_자식메소드와_REQUIRED_부모메소드(){
        //given
        Person_parent test = personRepository.save(Person_parent.builder().age(0).name("test").build());

        //when
        personService.parentMethod_1(test.getId(), 1, "test_update");

        //then
        assertEquals(test.getName(), "test");

    }

    @Test
    @DisplayName("[REQUIRED_NEW]자식메소드에서 예외가 일어날 때, 부모 메소드는 rollback 되지 않는다.")
    public void REQUIRED_NEW_자식메소드와_REQUIRED_부모메소드(){
        //given
        Person_parent test = personRepository.save(Person_parent.builder().age(0).name("test2").build());

        //when
        personService.parentMethod_2(test.getId(), 2, "test2_update");

        //then
        assertEquals(personRepository.findById(test.getId()).get().getName(), "test2_update");

    }
}