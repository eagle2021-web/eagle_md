package org.spring.mongodb.example.common.kits;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.reflect.Type;
public class FileKits {
    static String readFileToString(Path filePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(filePath);

        // 将字节流转换为字符串
        return new String(fileContent);
    }

    static <T> T readFileToObject(Path filePath,  Class<T> classOfT) throws IOException {
        String json = readFileToString(filePath);
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);

    }
}
