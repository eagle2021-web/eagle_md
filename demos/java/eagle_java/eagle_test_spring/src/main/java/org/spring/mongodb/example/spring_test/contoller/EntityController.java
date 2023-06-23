package org.spring.mongodb.example.spring_test.contoller;

import org.spring.mongodb.example.entity.Person;
import org.spring.mongodb.example.entity.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entity")
public class EntityController {

    @PostMapping("/person")
    public String person(@RequestBody Person person) {
        System.out.println(person);
        return "ok";
    }

    @PostMapping("/student")
    public String student(@RequestBody Student student) {
        System.out.println(student);
        return "ok";
    }
}
