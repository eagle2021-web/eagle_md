```xml
    <dependencies>
    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>2.7.8</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <version>2.7.8</version>
    </dependency>
    <!--        <dependency>-->
    <!--            <groupId>org.springframework</groupId>-->
    <!--            <artifactId>spring-test</artifactId>-->
    <!--            <scope>test</scope>-->
    <!--        </dependency>-->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.9.2</version>
        <scope>dev</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.9.2</version>
        <scope>test</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.3.1</version>
        <scope>test</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>5.3.1</version>
        <scope>test</scope>
    </dependency>
    <!--mybatis-plus-->
    <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.5.3</version>
    </dependency>

    <!--mybatis-plus 代码生成器-->
    <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-generator -->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-generator</artifactId>
        <version>3.5.3.1</version>
    </dependency>

    <!-- Mybatis Plus 代码生成器模板引擎,  -->
    <!-- https://mvnrepository.com/artifact/org.apache.velocity/velocity-engine-core -->
    <dependency>
        <groupId>org.apache.velocity</groupId>
        <artifactId>velocity-engine-core</artifactId>
        <version>2.3</version>
    </dependency>


    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.9.1</version>
    </dependency>
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
        <version>4.0.3</version>
    </dependency>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.19</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.20.0</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.20.0</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.28</version>
        <scope>provided</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2 -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-dbcp2</artifactId>
        <version>2.9.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.78</version>
    </dependency>
</dependencies>
```

```text
org.junit.jupiter:junit-jupiter-api 和 org.junit.jupiter:junit-jupiter-engine 这两个库在 JUnit Jupiter 测试框架中具有不同的功能和作用。
junit-jupiter-api:
artifactId: junit-jupiter-api
作用范围（scope）: dev
功能: junit-jupiter-api 库是编写 JUnit Jupiter 测试代码的核心依赖。它提供了用于编写测试的各种注解（如 @Test、@BeforeEach、@AfterEach 等），断言方法（如 assertEquals()、assertTrue() 等），以及其他 JUnit Jupiter 的核心特性。
详细说明: 这个库是在开发环境和生产环境都需要引入的，因为它包含了编写 JUnit Jupiter 测试所需的核心组件。

junit-jupiter-engine:
artifactId: junit-jupiter-engine
作用范围（scope）: test
功能: junit-jupiter-engine 库是执行 JUnit Jupiter 测试的运行时引擎。它负责将编写的测试代码实际运行起来，并提供一些测试运行时的环境支持。
详细说明: 这个库通常只在测试环境中需要引入，它会根据你编写的测试代码来执行相应的测试，并生成测试报告和结果。
总结来说，junit-jupiter-api 库提供了编写 JUnit Jupiter 测试所需的核心组件，而 junit-jupiter-engine 库负责运行这些测试代码。通过引入这两个库，你可以完成 JUnit Jupiter 测试的编写和执行，并获得相应的测试报告和结果。
```
```xml
    <build>
        <!--                java '-Dfile.encoding=utf-8' -jar -->
        <!--                java -Dfile.encoding=utf-8 -jar -->
        <!--        项目打包时会将Java目录中的*.xml文件也进行打包-->
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            <!--        上述XML片段是Maven的POM文件中关于资源过滤器（resource filtering）的配置。这段配置表明在构建项目时，
            会将src/main/java目录下的所有.xml文件包括到编译后的输出中，同时禁用资源过滤。
            对于每个被包含的.xml文件，Maven会执行过滤器的替换操作，将其中的占位符（如${variable}）替换为实际的值。但在这里，
            由于<filtering>false</filtering>配置了false，表示禁用了资源过滤，所以不会进行替换操作，直接将原始内容复制到输出目录中。
            这样做可以保留.xml文件中的特殊字符和变量占位符，适用于配置文件等需要保持原始内容的场景。
            如果你需要启用资源过滤器，你可以将<filtering>修改为true，这样在构建过程中就会按照Maven的默认规则对资源文件进行过滤替换操作。-->
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>*.yaml</include>
                    <include>*.xml</include>
                    <include>*.yml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
```

```xml
        <plugins>
            <plugin>
                <groupId>org.cyclonedx</groupId>
                <artifactId>cyclonedx-maven-plugin</artifactId>
                <version>2.7.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>makeAggregateBom</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <projectType>library</projectType>
                    <schemaVersion>1.4</schemaVersion>
                    <includeBomSerialNumber>true</includeBomSerialNumber>
                    <includeCompileScope>true</includeCompileScope>
                    <includeProvidedScope>true</includeProvidedScope>
                    <includeRuntimeScope>true</includeRuntimeScope>
                    <includeSystemScope>true</includeSystemScope>
                    <includeTestScope>false</includeTestScope>
                    <includeLicenseText>false</includeLicenseText>
                    <outputReactorProjects>true</outputReactorProjects>
                    <outputFormat>all</outputFormat>
                    <outputName>bom</outputName>
                    <verbose>false</verbose><!-- = ${cyclonedx.verbose} -->
                </configuration>
            </plugin>
        </plugins>
```
