#![allow(unused_variables)]

//  在构思的过程中放宽编译器警告。
type File = String;

//  创建一个类型别名。编译器不会去区分String和File，但是源代码会。
fn open(f: &mut File) -> bool {
    true    //  现在我们暂时假定这两个函数总是会成功的。
}

fn close(f: &mut File) -> bool {
    true    //
}

#[allow(dead_code)]    //  放宽一个未使用函数的编译器警告。
fn read(f: &mut File, save_to: &mut Vec<u8>) -> ! {    //  ! 返回类型会告知Rust编译器，此函数永不返回。
    println!("{}", 111);
    unimplemented!()    //  如果执行到这个宏，那么程序会崩溃。
}

fn main() {
    let mut f1 = File::from("f1.txt");    //  在第3行中声明的类型，File“继承”了String类型的所有方法。
    open(&mut f1);
    let mut v:Vec<u8> = vec![];
    // read(&mut f1, &mut v);    //  此时调用此方法毫无意义。
    close(&mut f1);
}