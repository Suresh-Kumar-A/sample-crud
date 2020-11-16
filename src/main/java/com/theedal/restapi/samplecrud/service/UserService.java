package com.theedal.restapi.samplecrud.service;

import com.theedal.restapi.samplecrud.model.User;
import com.theedal.restapi.samplecrud.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void deleteByUid(String uid) throws Exception {
        User user = findByUid(uid);
        if (user != null)
            deleteUser(user);
        else
            throw new Exception("User Does not Exists");
    }

    public User updateUser(User user) throws Exception {
        User temp = findByUid(user.getUid());
        if (temp != null) {
            if (user.getName() != null && user.getName().trim() != "") {
                temp.setName(user.getName());
            }
            if (user.getInitial() != null && user.getInitial().trim() != "") {
                temp.setInitial(user.getInitial());
            }
            if (user.getEmail() != null && user.getEmail().trim() != "") {
                temp.setEmail(user.getEmail());
            }
            if (user.getPhonenumber() != null && user.getPhonenumber().trim() != "") {
                temp.setPhonenumber(user.getPhonenumber());
            }
            if (user.getAddress() != null && user.getAddress().trim() != "") {
                temp.setAddress(user.getAddress());
            }
            return userRepository.save(temp);
        } else
            throw new Exception("User Does not Exists");
    }
}
