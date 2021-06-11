package com.example.springboot.service;

public interface test {
    default void test() { // default method in java8(require body)
        System.out.println("This is method test!!!");
    }

    void testtABC();
}
