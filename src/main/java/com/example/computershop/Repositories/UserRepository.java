package com.example.computershop.Repositories;

import com.example.computershop.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByEmail(String email);
    User getByName(String name);
    Optional<User> findByEmail(String email);
}
