package com.banking.egbank.modules.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.egbank.modules.role.entities.RoleEntity;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
