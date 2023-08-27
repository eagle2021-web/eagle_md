#![allow(unused_variables)]    //  关掉一些警告。
#[derive(Debug)]    //  让File可以在println! 宏和它的兄弟fmt! 宏中使用（在此清单的底部用到了）。
struct File{
    name: String,
    data: Vec<u8>,
}
fn open(f: &mut File) -> bool{    //  现在，这两个函数仍然是无作用的状态。
    true
}
fn close(f: &mut File) -> bool{    //
    true
}
fn read(
    f: &File,
    save_to: &mut Vec<u8>,
) -> usize{    //  返回读取的字节数。
    let mut tmp = f.data.clone();    //  创建了一个data的副本，因为save_to.append() 将会清空输入的Vec<T>。
    let read_length = tmp.len();
    save_to.reserve(read_length);    //  确保有足够的空间来容纳要传入的数据。
    save_to.append(&mut tmp);    //  在save_to缓冲区中分配足够的数据，以保存f中的内容。
    return read_length;
}
fn main(){
    let mut f2 = File{
        name: String::from("2.txt"),
        data: vec![114, 117, 115, 116, 33],
    };
    let mut buffer: Vec<u8> = vec![];
    open(&mut f2);    //  与文件进行交互的具体工作。
    let f2_length = read(&f2, &mut buffer);
    close(&mut f2);
    let text = String::from_utf8_lossy(&buffer);    //  将Vec<u8> 转换为String。任何包含无效UTF-8的字节数据都会被替换成 �（特殊字符）。
    println!("{:?}", f2);
    println!("{} is{} bytes long", &f2.name, f2_length);
    println!("{}", text)    //  把字节114、117、115、116以及33作为实际的文字来查看。
}