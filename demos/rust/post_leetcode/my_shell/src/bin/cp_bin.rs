use std::process::Command;

fn main() {
    let mut c = Command::new("cp");
    c.args(["-u","./target/release/*.exe", "d:/bin"]);
    c.status().expect("cp err");
    println!("ok");
}