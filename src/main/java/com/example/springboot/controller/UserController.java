package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * @return list User
     */
    @CrossOrigin
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
//    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<List<User>> getALLUser() {
        log.debug("---------------REST request to getAllUser--------------------");
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
//        return userService.getAllUsers();
    }

    /**
     * @param user
     * @return user
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        log.debug("---------------REST request to addUser--------------------");
        return this.userService.addUser(user);
    }

    /**
     *
     * @param id
     */
    @RequestMapping(value = "/deleteUserById/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable(value = "id") Integer id) {
        log.debug("---------------REST request to delete by id--------------------");
        userService.deleteUserById(id);
    }

    /**
     *
     * @param id
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam(value = "id", required = true) Integer id) {
        log.debug("---------------REST request to delete by id--------------------");
        userService.deleteUserById(id);
    }

}
