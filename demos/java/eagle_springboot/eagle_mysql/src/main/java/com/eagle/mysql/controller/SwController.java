package com.eagle.mysql.controller;

import com.eagle.mysql.service.SwService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController()
@RequestMapping("/sw")
public class SwController {
    @Resource
    private SwService service;
    @PostMapping("/save")
    public String saveFile(@RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            inputStream.close();
//            String content = new String(file.getBytes());
            System.out.println(content);
            service.saveJson(content);

            return "File saved successfully!";
        } catch (Exception e) {
            // Handle exception
            return "Error saving file: " + e.getMessage();
        }
    }
}
