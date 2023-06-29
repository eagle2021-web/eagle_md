# eagle_java
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
                    <outputDirectory>${project.build.directory}</outputDirectory><!-- usually target, if not redefined in pom.xml -->
                    <verbose>false</verbose><!-- = ${cyclonedx.verbose} -->
                </configuration>
            </plugin>
        </plugins>
```
```shell

```
