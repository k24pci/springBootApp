package com.ucx.training.sessions.app.repository;

import com.ucx.training.sessions.app.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
