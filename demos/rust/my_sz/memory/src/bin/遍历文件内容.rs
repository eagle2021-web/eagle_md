use std::fs::File;
use std::io::prelude::*;
use std::env;
const BYTES_PER_LINE: usize = 16;    //-  修改这个常量的值，可以改变程序的输出信息。
fn main(){
    let mut arg1 = env::args().nth(1);
    if arg1.is_none() {
        arg1 = Some("E:/projects/md/eagle_md/demos/rust/my_sz/Cargo.toml".to_string());
    }
    let fname = arg1.expect("usage: fview FILENAME");
    let mut f = File::open(&fname).expect("Unable to open file.");
    let mut pos = 0;
    let mut buffer = [0; BYTES_PER_LINE];
    while let Ok(_) = f.read_exact(&mut buffer){
        print!("[0x{:08x}] ", pos);
        for byte in &buffer{
            match *byte{
                0x00 => print!(". "),
                0xff => print!("## "),
                _ => print!("{:02x} ", byte),
            }
        }
        println!();
        pos += BYTES_PER_LINE;
    }
}