use rand::prelude::*;

//  将rand包中的常用的trait和类型导入本包的作用域中。
fn one_in(denominator: u32) -> bool {    //  辅助函数让我们能够偶尔触发错误。
    thread_rng().gen_ratio(1, denominator)    //  thread_rng() 创建一个线程局部随机数生成器，gen_ratio(n, m) 会以n/m的概率来返回一个布尔值。
}

#[derive(Debug)]
struct File {
    name: String,
    data: Vec<u8>,
}

impl File {
    fn new(name: &str) -> File {
        File {
            name: String::from(name),
            data: Vec::new(),
        }    //  代码格式上的改变缩短了此代码块。
    }
    fn new_with_data(name: &str, data: &Vec<u8>) -> File {
        let mut f = File::new(name);
        f.data = data.clone();
        f
    }
    fn read(
        self: &File,
        save_to: &mut Vec<u8>,
    ) -> Result<usize, String> {     //  首次出现Result<T, E>，其中T是整数类型usize，E是String类型。使用String让我们能够提供任意错误消息。
        let mut tmp = self.data.clone();
        let read_length = tmp.len();
        save_to.reserve(read_length);
        save_to.append(&mut tmp);
        Ok(read_length)    //  在此代码中，read() 永远不会失败，但是我们还是将read_length包装在Ok中了，这是因为我们需要返回Result。
    }
}

fn open(f: File) -> Result<File, String> {
    if one_in(10_000) {    //  执行10000次，有1次会返回错误。
        let err_msg = String::from("Permission denied");
        return Err(err_msg);
    }
    Ok(f)
}

fn close(f: File) -> Result<File, String> {
    if one_in(100_000) {    //  执行100000次，有1次会返回错误。
        let err_msg = String::from("Interrupted by signal!");
        return Err(err_msg);
    }
    Ok(f)
}

fn main() {
    let f4_data: Vec<u8> = vec![114, 117, 115, 116, 33];
    let mut f4 = File::new_with_data("4.txt", &f4_data);
    let mut buffer: Vec<u8> = vec![];
    f4 = open(f4).unwrap();    //  从Ok中解包装T，然后留下T。
    let f4_length = f4.read(&mut buffer).unwrap();
    f4 = close(f4).unwrap();
    let text = String::from_utf8_lossy(&buffer);
    println!("{:?}", f4);
    println!("{} is{} bytes long", &f4.name, f4_length);
    println!("{}", text);
    let _f = File::new2("f1.txt");
}