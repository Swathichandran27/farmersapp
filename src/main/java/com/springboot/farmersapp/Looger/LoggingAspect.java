package com.springboot.farmersapp.Looger;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.springboot.farmersapp.Entity.Users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Define a pointcut for the createUser method in UserService
    @Pointcut("execution(* com.example.demo.Service.UserService.createUser(..))")
    public void createUserMethod() {}

    // Before advice: Executes before createUser method execution
    @Before("createUserMethod()")
    public void logBeforeCreateUser() {
        logger.info("Creating a new user...");
    }

    // After returning advice: Executes after createUser method execution successfully
    @AfterReturning(pointcut = "createUserMethod()", returning = "result")
    public void logAfterCreateUser(Object result) {
        logger.info("Successfully created user with ID: {}", ((Users) result).getId());
    }

    // Define a pointcut for the getUserById method in UserService
    @Pointcut("execution(* com.example.demo.Service.UserService.getUserById(..))")
    public void getUserByIdMethod() {}

    // Before advice: Executes before getUserById method execution
    @Before("getUserByIdMethod()")
    public void logBeforeGetUserById() {
        logger.info("Fetching user by ID...");
    }

    // After returning advice: Executes after getUserById method execution successfully
    @AfterReturning(pointcut = "getUserByIdMethod()", returning = "result")
    public void logAfterGetUserById(Object result) {
        if (result != null) {
            logger.info("Successfully fetched user with ID: {}", ((Users) result).getId());
        } else {
            logger.info("No user found with the given ID.");
        }
    }

    // Define a pointcut for the getAllUsers method in UserService
    @Pointcut("execution(* com.example.demo.Service.UserService.getAllUsers(..))")
    public void getAllUsersMethod() {}

    // Before advice: Executes before getAllUsers method execution
    @Before("getAllUsersMethod()")
    public void logBeforeGetAllUsers() {
        logger.info("Fetching all users...");
    }

    // After returning advice: Executes after getAllUsers method execution successfully
    @AfterReturning(pointcut = "getAllUsersMethod()", returning = "result")
    public void logAfterGetAllUsers(Object result) {
        logger.info("Successfully fetched all users, count: {}", ((List<?>) result).size());
    }

    // Define a pointcut for the updateUser method in UserService
    @Pointcut("execution(* com.example.demo.Service.UserService.updateUser(..))")
    public void updateUserMethod() {}

    // Before advice: Executes before updateUser method execution
    @Before("updateUserMethod()")
    public void logBeforeUpdateUser() {
        logger.info("Updating user...");
    }

    // After returning advice: Executes after updateUser method execution successfully
    @AfterReturning(pointcut = "updateUserMethod()", returning = "result")
    public void logAfterUpdateUser(Object result) {
        logger.info("Successfully updated user with ID: {}", ((Users) result).getId());
    }

    // Define a pointcut for the deleteUser method in UserService
    @Pointcut("execution(* com.example.demo.Service.UserService.deleteUser(..))")
    public void deleteUserMethod() {}

    // Before advice: Executes before deleteUser method execution
    @Before("deleteUserMethod()")
    public void logBeforeDeleteUser() {
        logger.info("Deleting user...");
    }

    // After returning advice: Executes after deleteUser method execution successfully
    @AfterReturning(pointcut = "deleteUserMethod()")
    public void logAfterDeleteUser() {
        logger.info("Successfully deleted a user.");
    }
}