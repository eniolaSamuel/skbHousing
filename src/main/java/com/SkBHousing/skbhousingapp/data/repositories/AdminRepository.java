package com.SkBHousing.skbhousingapp.data.repositories;

import com.SkBHousing.skbhousingapp.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AdminRepository extends JpaRepository<Admin, Long> {
}
