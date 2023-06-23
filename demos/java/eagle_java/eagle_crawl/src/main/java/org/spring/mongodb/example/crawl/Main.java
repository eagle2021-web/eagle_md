package org.spring.mongodb.example.crawl;


import okhttp3.*;

import java.io.*;
import java.nio.CharBuffer;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class A {


    public static void main(String[] args) {
        String originalString = "https://developer.aliyun.com/browse/tree?repoId=central&path=#。com/&pageStart=0&pageSize=50&versionType=maven&_input_charset=utf-8";

        try {
            // 使用指定字符集进行编码（这里使用UTF-8）
            String encodedString = URLEncoder.encode(originalString, "UTF-8");
            System.out.println("Encoded String: " + encodedString);
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = null;
        Request request = new Request.Builder()
                .url("https://repository.jboss.org/nexus/content/groups/public/")
                .method("GET", body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("body");
        System.out.println(response.body());
        if (response.code() == 200) {
            assert response.body() != null;
            Reader reader = response.body().charStream();
//            InputStreamReader reader = new InputStreamReader(inputStream);
            char[] bytes = new char[512];
            StringBuffer sb = new StringBuffer();
            int cnt = 0;
            while ((cnt = reader.read(bytes, 0, bytes.length)) != -1) {
                sb.append(bytes, 0, cnt);
            }
            System.out.println(sb);
        }
    }
}
