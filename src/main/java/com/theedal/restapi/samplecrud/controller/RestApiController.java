package com.theedal.restapi.samplecrud.controller;

import com.theedal.restapi.samplecrud.model.User;
import com.theedal.restapi.samplecrud.service.UserService;
import com.theedal.restapi.samplecrud.utils.CustomUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class RestApiController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/status")
    public ResponseEntity<String> checkStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            CustomUtils utils = new CustomUtils();
            user.setUid(utils.generateToken());
            return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/view-all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        try {
            return new ResponseEntity<Iterable<User>>(userService.findAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/view/{uid}")
    public ResponseEntity<User> getUserByUid(@PathVariable(value = "uid") String uid) {
        try {
            return new ResponseEntity<User>(userService.findByUid(uid), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/user/delete/{uid}")
    public ResponseEntity<String> deleteUserByUid(@PathVariable(value = "uid") String uid) {
        try {
            userService.deleteByUid(uid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
