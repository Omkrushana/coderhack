package com.omkrushana.coderhack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkrushana.coderhack.model.User;
public interface IUserRepository extends JpaRepository<User,Long> {
    
}
