package com.example.transactional.repository;

import com.example.transactional.entity.PersonLog_child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonLogRepository extends JpaRepository<PersonLog_child, Long> {
}
