package com.theedal.restapi.samplecrud.repository;

import com.theedal.restapi.samplecrud.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUid(String uid);    
    
}
