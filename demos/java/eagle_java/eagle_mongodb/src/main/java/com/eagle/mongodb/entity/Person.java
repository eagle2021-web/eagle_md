package com.eagle.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "Person")
@TypeAlias("Person")
public class Person {
        private Integer ssn;
        private String firstName;
        private String Fraizer;
        private String address;
}
