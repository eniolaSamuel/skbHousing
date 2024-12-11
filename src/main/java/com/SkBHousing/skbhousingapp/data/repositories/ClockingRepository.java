package com.SkBHousing.skbhousingapp.data.repositories;

import com.SkBHousing.skbhousingapp.data.models.Clocking;
import com.SkBHousing.skbhousingapp.data.models.Clocking_Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClockingRepository extends JpaRepository<Clocking, Long> {

    Clocking findByPhoneNumberAndClockingStatus(String customerPhoneNumber, Clocking_Status clockingStatus);
}
