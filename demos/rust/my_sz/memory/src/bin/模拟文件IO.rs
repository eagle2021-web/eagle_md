use std::io::prelude::*;    //-  prelude导入了在I/O操作中常用的一些trait，例如Read和Write。你也可以手动来添加这些trait，但是由于它们很常用，所以标准库就提供了这种便利性，这样可以让你的代码更精简。
const BYTES_PER_LINE: usize = 16;
//-  当你使用原始字符串字面量（raw string literal）来构建多行的字符串字面量时，双引号是不需要转义的（注意这里的r前缀和#分隔符）。额外的那个b前缀表示，应该把这里的字面量数据视为字节数据（&[u8]），而不是UTF-8文本数据（&str）。
const INPUT: &'static [u8] = br#"
fn main(){
println!("Hello, world!");
}"#;
fn main() -> std::io::Result<()>{
    let mut buffer: Vec<u8> = vec!();    //-  创建出内部缓冲区的空间，供程序的输入来使用。
    INPUT.read_to_end(&mut buffer)?;    //-  读取输入信息，并将其插入内部缓冲区。
    let mut position_in_input = 0;
    for line in buffer.chunks(BYTES_PER_LINE){ // 16个u8为一块
        print!("[0x{:08x}] ", position_in_input);    //-  输出当前位置的信息，最多8位，不足8位则在左侧用零填充。
        for byte in line{
            print!("{:02x} ", byte);
        }
        println!();    //-  输出一个换行符到标准输出中，这是一种简便的方法。
        position_in_input += BYTES_PER_LINE;

    }
    Ok(())
}