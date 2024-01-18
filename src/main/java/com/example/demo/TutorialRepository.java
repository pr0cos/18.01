package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TutorialRepository extends JpaRepository<User,
        Long> {
    List<User> findByAge(int age);
    List<User> findByUsernameContaining(String username);
}