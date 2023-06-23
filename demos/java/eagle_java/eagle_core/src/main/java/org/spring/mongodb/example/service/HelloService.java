package org.spring.mongodb.example.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public void sayHello(){
        System.out.println("hello service!");
    }
}
