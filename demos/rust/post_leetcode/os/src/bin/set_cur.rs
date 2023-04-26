use std::env;
use std::process::Command;

fn main() {
    // 设置新的工作目录
    let path = "/path/to/new/directory";
    env::set_current_dir(&path).expect("failed to change working directory");

// 执行命令
    let output = Command::new("cargo")
        .arg("build")
        .output()
        .expect("failed to execute command");
    println!("{}", String::from_utf8_lossy(&output.stdout));

}