package com.eagle.common.kits;

import lombok.ToString;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class TestJsonToEntity {
    Path resourceDirectory = Paths.get("src", "test", "resources");
    @ToString
    static class Person {
        private String name;
        private int version;

        public Person(String name, int version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public int getVersion() {
            return version;
        }


    }

    @Test
    public void readFile() throws IOException {

        Person person = FileKits.readFileToObject(resourceDirectory.resolve("1.json"), Person.class);
        Assertions.assertEquals(person.name, "eagle");
        Person person2 = FileKits.readFileToObject(resourceDirectory.resolve("2.json"), Person.class);
        Assertions.assertEquals(person2.name, "eagle2");
    }

}

