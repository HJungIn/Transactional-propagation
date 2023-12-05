package com.example.transactional.repository;

import com.example.transactional.entity.Person_parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person_parent, Long> {
}
