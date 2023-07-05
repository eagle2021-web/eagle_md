package com.eagle.gen_maven;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String filePath = "path/to/your/file.txt";
        String content = "Hello, world!";
        System.out.println("+++++++++++++");
        System.out.println(Arrays.toString(args));
//        try {
//            File file = new File(filePath);
//            FileUtils.writeStringToFile(file, content, "UTF-8");
//
//            System.out.println("File written successfully!");
//        } catch (IOException e) {
//            System.err.println("Error writing file: " + e.getMessage());
//        }
    }
}
