package com.example.shopboard.repository;

import com.example.shopboard.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <UserEntity, Long> {

}
