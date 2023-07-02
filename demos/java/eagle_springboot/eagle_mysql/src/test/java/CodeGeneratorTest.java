import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.sql.Types;
import java.util.Collections;

public class CodeGeneratorTest {

    @Test
    public void genCode() {
        String url = "jdbc:mysql://h131:3306/hsp_mybatis?useSSL=true&requireSSL=true&verifyServerCertificate=false&characterEncoding=UTF-8";
        String projectPath = System.getProperty("user.dir");
        String root_java = projectPath +  "/src/test/java";
        System.out.println(root_java);
        System.out.println("root");
        String comPrefix = "/com/eagle/mysql";
        FastAutoGenerator.create(url, "root", "123456")
                .globalConfig(builder -> {
                    builder.author("eagle") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(root_java); // 指定输出目录
                })

                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent(comPrefix) // 设置父包名
//                            .moduleName("abc") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, root_java + comPrefix + "/mapper/xml")) // 设置mapperXml生成路径
                    ;
                })
                .strategyConfig(builder -> {
                    builder.addInclude("monster") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_")

                            .controllerBuilder()
                            .enableRestStyle()
                    ; // 设置过滤表前缀

                })

//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
