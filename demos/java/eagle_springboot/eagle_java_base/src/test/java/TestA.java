import com.alibaba.fastjson.JSONObject;
import com.eagle.base.pojo.toJSON.Property1_0Beta;
import com.eagle.base.pojo.toJSON.Property1_2;
import com.eagle.base.pojo.toJSON.PropertyAbs;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import org.apache.commons.io.IOUtils;
public class TestA {
    @Test
    public void aa(){
        InputStream stream = ClassLoader.getSystemResourceAsStream("1.json");
        try {
            assert stream != null;
            String content = IOUtils.toString(stream, StandardCharsets.UTF_8);
//            Property1_0Beta property = JSONObject.parseObject(content, Property1_0Beta.class);
            PropertyAbs property = JSONObject.parseObject(content, PropertyAbs.class);
            System.out.println(property);
            System.out.println(property.getClass());
            System.out.println(property.getName());
            // 使用转换后的字符串进行操作
            System.out.println(content);
        } catch (IOException e) {
            // 异常处理
        } finally {
            // 关闭输入流
            IOUtils.closeQuietly(stream);
        }

    }
    @Test
    public void a1_2(){
        InputStream stream = ClassLoader.getSystemResourceAsStream("2.json");
        try {
            assert stream != null;
            String content = IOUtils.toString(stream, StandardCharsets.UTF_8);
            System.out.println(content);
//            Property1_0Beta property = JSONObject.parseObject(content, Property1_0Beta.class);
            PropertyAbs property = JSONObject.parseObject(content, PropertyAbs.class);
            System.out.println(property);
            System.out.println("------");
            property = JSONObject.parseObject(content, Property1_2.class);
            System.out.println(property);
            System.out.println("-------");
            System.out.println(property.getClass());
            System.out.println(property.getName());
            // 使用转换后的字符串进行操作
            System.out.println(content);
        } catch (IOException e) {
            // 异常处理
        } finally {
            // 关闭输入流
            IOUtils.closeQuietly(stream);
        }

    }
}
