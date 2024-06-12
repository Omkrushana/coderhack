package com.omkrushana.coderhack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.omkrushana.coderhack.model.User;
@Repository
public interface IUserRepository extends MongoRepository<User, String> {
// public interface IUserRepository extends JpaRepository<User,String> {
    
}
