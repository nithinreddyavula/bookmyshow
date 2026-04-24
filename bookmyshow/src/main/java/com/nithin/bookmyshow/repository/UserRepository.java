package com.nithin.bookmyshow.repository;

import com.nithin.bookmyshow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByUserEmail(String userEmail);
}
