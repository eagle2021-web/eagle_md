package com.eagle.spring_test.contoller;

import com.eagle.entity.Person;
import com.eagle.entity.Student;
import lombok.Data;
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
