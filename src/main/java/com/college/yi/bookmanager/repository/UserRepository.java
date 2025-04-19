package com.college.yi.bookmanager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.college.yi.bookmanager.entity.UserEntity;
@Mapper
public interface UserRepository {
    
    List<UserEntity>findAll();
    
    UserEntity findByUsername(@Param("username")String username);
    
    void insert(UserEntity user);
    
    void update(UserEntity user);
    
    void delete(Long id);

}
