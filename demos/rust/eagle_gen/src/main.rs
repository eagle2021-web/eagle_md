use std::fs::{self, File};
use std::io::Read;
use std::io::Write;
use std::path::PathBuf;
use std::process::Command;
use tempfile::tempdir;
use structopt::StructOpt;
#[derive(Debug, StructOpt)]
#[structopt(name = "example", about = "An example command line parser")]
struct Opt {
    #[structopt(short, long, default_value= "com.baomidou")]
    group_id: String,

    #[structopt(short, long, default_value = "mybatis-plus-generator")]
    artifact_id: String,

    #[structopt(short, long, default_value = "3.5.3.1")]
    version: String,

}
const TEMPLATE_CONTENT: &str = r#"<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <!-- 定义项目的坐标信息 -->
    <groupId>com.example</groupId>
    <artifactId>myproject</artifactId>
    <version>1.0.0</version>
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            $1
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.cyclonedx</groupId>
                <artifactId>cyclonedx-maven-plugin</artifactId>
                <version>2.7.9</version>
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
                    <outputName>bom222</outputName>
                    <verbose>false</verbose>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
"#;
fn main() {
  
    let opt = Opt::from_args();
    println!("{} {} {}",opt.group_id, opt.artifact_id, opt.version);
    let temp_dir = tempdir().expect("无法创建临时目录");
    let mut temp_dir_path = temp_dir.path().to_owned();

    // 创建 pom.xml 文件路径
    let pom_file_path = temp_dir_path.join("pom.xml");

    // 创建 pom.xml 文件并写入内容
    let mut pom_file = File::create(&pom_file_path).expect("无法创建 pom.xml 文件");   
    let s = format!("<groupId>{}</groupId>
    <artifactId>{}</artifactId>
    <version>{}</version>", opt.group_id, opt.artifact_id, opt.version);
    let content = TEMPLATE_CONTENT.replace("$1", &s);
    write!(
        pom_file, "{}", content
    )
    .expect("无法写入 pom.xml 文件内容");

    println!(
        "已创建临时目录：{}，并在其中创建了 pom.xml 文件",
        temp_dir_path.display(),
    );
    if pom_file_path.exists() {
        println!("pom.xml 文件已创建：{}", pom_file_path.display());
    } else {
        println!("pom.xml 文件不存在");
    }
    let mut mvn_cmd = if cfg!(target_os = "windows") {
        println!("{}", "windows");
        let mut cmd = Command::new("mvn.cmd");
        cmd
    } else {
        println!("{}", "not windows");
        let mut cmd = Command::new("mvn");
        cmd
    };
    // 执行 mvn package 命令
    let output = mvn_cmd
        .arg("package")
        .current_dir(&temp_dir_path)
        .output()
        .expect("无法执行 mvn package 命令");

    if output.status.success() {
        println!("mvn package 命令执行成功");
        let bom_file_path = temp_dir_path.join("target/bom222.json");
        println!("{}", bom_file_path.display());
        // 读取 bom22.json 文件内容
        let mut bom_file = File::open(&bom_file_path).expect("打开文件失败");
        let mut bom_contents = String::new();
        bom_file.read_to_string(&mut bom_contents).expect("读失败");
        let mut tmp_json = File::create("tmp.json").expect("创建失败");
        write!(tmp_json, "{}", bom_contents).unwrap();
        println!("bom22.json 文件内容为:\n{}", bom_contents);
    } else {
        let stderr = output.stderr.clone();
        if !stderr.is_empty() {
            if let Ok(stderr_str) = String::from_utf8(stderr) {
                println!("Error Message: {}", stderr_str);
            } else {
                println!("Failed to decode stderr as UTF-8");
            }
        }
        println!("mvn package 命令执行失败");
    }
    let stdout_string = String::from_utf8_lossy(&output.stdout);
    println!("Standard Output: {}", stdout_string);

    let stderr_string = String::from_utf8_lossy(&output.stderr);
    println!("Standard Error: {}", stderr_string);
}
