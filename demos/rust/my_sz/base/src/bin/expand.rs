static GLOBAL: i32 = 1000;

// 创建一个全局静态（global static）变量，在Rust程序中，这是一个全局变量。
fn noop() -> *const i32 {
    let noop_local = 12345;    // 在noop()中创建一个局部变量，它存在于main()函数外部的一个内存地址中。
    &noop_local as *const i32    // 把noop_local的地址转换为一个原始指针，然后返回这个原始指针。
}

fn main() {
    let local_str = "a";    // 创建了多个类型的值，其中一些值是在堆上的。
    let local_int = 123;
    let boxed_str = Box::new('b');
    let boxed_int = Box::new(789);
    let fn_int = noop();
    println!("GLOBAL:   {:p}", &GLOBAL as *const i32);
    println!("local_str:{:p}", local_str as *const str);
    println!("local_int:{:p}", &local_int as *const i32);
    println!("boxed_int:{:p}", Box::into_raw(boxed_int));
    println!("boxed_str:{:p}", Box::into_raw(boxed_str));
    println!("fn_int:   {:p}", fn_int);

    let b2 = Box::new(1);
    println!("fn_int:   {:p}", Box::into_raw(b2));
    let x = Box::new(String::from("Hello"));
    println!("fn_int:   {}", x);
    let ptr = Box::into_raw(x);
    println!("fn_int:   {:p}", ptr);
    let x2 = unsafe { Box::from_raw(ptr) };
    println!("fn_int:   {}", x2);
    println!("fn_int:   {:p}", Box::into_raw(x2));
}
