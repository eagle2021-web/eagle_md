use std::mem::size_of;

static B: [u8; 10] = [99, 97, 114, 114, 121, 116, 111, 119, 101, 108];
static C: [u32; 11] = [116, 104, 97, 110, 107, 115, 102, 105, 115, 104, 0];

fn main() {
    let a: usize = 42;
    let b: &[u8; 10] = &B;
    let c: Box<[u32]> = Box::new(C);

    println!("C (an array of 11 bytes):");
    println!("  location: {:p}", &C); //#1
    println!("  location: {:p}", &C[0]); // 打印数组C第一个元素的地址
    println!("  size:     {:?} bytes", size_of::<[u32; 11]>()); // 数组类型应为 u32，不是 u8
    println!("  value:    {:?}", C);
    println!();

    println!("c (a \"box\" for C):");
    println!("  location: {:p}", &c);
    let ref2 = c.as_ptr();
    unsafe {
        println!("  location as ptr: {:?}", *ref2);
        println!("  location as ptr: {:p}", ref2);
        println!("  location as ptr: {:p}", &*ref2); // 此处的打印方式是正确的 #2为什么#1的地址不一样
        println!("  location as ptr: {:p}", ref2 as *const [u32; 11]); // 强制转换为 &[u32; 11] 类型
    }
    println!("  size:     {:?} bytes", size_of::<Box<[u32]>>()); // 相应地修改字节长度
    println!("  points to: {:p}", c);
    println!();

    println!("a (an unsigned integer):");
    println!("  location: {:p}", &a);
    println!("  size:     {:?} bytes", size_of::<usize>());
    println!("  value:    {:?}", a);
    println!();

    println!("b (a reference to B):");
    println!("  location: {:p}", &b);
    println!("  size:     {:?} bytes", size_of::<&[u8; 10]>());
    println!("  points to: {:p}", b);
    println!();

    println!("B (an array of 10 bytes):");
    println!("  location: {:p}", &B);
    println!("  size:     {:?} bytes", size_of::<[u8; 10]>());
    println!("  value:    {:?}", B);
}