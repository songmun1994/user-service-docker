package com.studyproject.userservicedocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.studyproject.userservicedocker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}