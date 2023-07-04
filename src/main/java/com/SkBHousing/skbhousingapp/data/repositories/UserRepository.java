package com.SkBHousing.skbhousingapp.data.repositories;

import com.SkBHousing.skbhousingapp.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
}
