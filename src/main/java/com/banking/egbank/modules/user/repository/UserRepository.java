package com.banking.egbank.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.banking.egbank.modules.user.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // @Query("SELECT u FROM UserEntity u WHERE LOWER(u.email) = LOWER(:email)")
    // Optional<UserEntity> findByEmailIgnoreCase(@Param("email") String email);
    UserEntity findByEmail(@Param("email") String email);
}
