package com.eagle.common.kits;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestJsonToEntity {
    @Test
    public void readFile() throws IOException {
        // 获取测试资源目录的路径
        Path resourceDirectory = Paths.get("src", "test", "resources");

        // 拼接要读取的文件名或相对路径
        Path filePath = resourceDirectory.resolve("1.json");

        // 读取文件内容
        byte[] fileContent = Files.readAllBytes(filePath);

        // 将字节流转换为字符串
        String content = new String(fileContent);

        System.out.println(content);
    }

    public static void main(String[] args) {

    }
}

