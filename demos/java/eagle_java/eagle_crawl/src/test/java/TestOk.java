import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.commons.text.StringEscapeUtils;
public class TestOk {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://www.zenodo.org/api/records/?page=1&size=20&keywords=Taxonomy")
                .method("GET", null)
                .addHeader("Cookie", "session=5946bb05d261afba_649c0c04.ACAw69hWMzeAnBEjeiJaheQ15t0")
                .addHeader("Accept-Encoding", "application/json; charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();

        if (response.code() == 200) {
            assert response.body() != null;
            String content = response.body().string();
//            InputStream inputStream = response.body().byteStream();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//
//            byte[] bytes = outputStream.toByteArray();
//            String content = new String(bytes, StandardCharsets.UTF_8);
//            System.out.println(sb);
            File outputFile = new File("output2.json");

            Files.asCharSink(outputFile, StandardCharsets.UTF_8).write(content);
        }

    }

    @Test
    public void aa(){
        try {
            String filePath = "E:/projects/md/eagle_md/demos/java/eagle_java/output2.json";

            // 读取JSON文件内容
            byte[] encodedBytes =  java.nio.file.Files.readAllBytes(Paths.get(filePath));
            String jsonContent = new String(encodedBytes, StandardCharsets.UTF_8);


            // 使用yourObject进行相关操作
            System.out.println(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void adds(){
        String input = "\\u1e42&Hcirc;\\u1e44\\u1e41ae";

        // 解码Unicode转义序列
        String decodedString = StringEscapeUtils.unescapeJava(input);

        // 将解码后的字符串转换成UTF-8字节数组，再根据UTF-8编码规则获取字符串
        byte[] utf8Bytes = decodedString.getBytes(StandardCharsets.UTF_8);
        String utf8String = new String(utf8Bytes, StandardCharsets.UTF_8);

        System.out.println(utf8String);
    }
}
