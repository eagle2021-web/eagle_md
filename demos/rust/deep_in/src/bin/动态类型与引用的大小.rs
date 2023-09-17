use std::mem;

trait Bird {
    fn fly222(&self);
    fn fly(&self);
}

struct Duck {
    pub get: i32,
    pub get2: i64,
    pub get3: i64,
}

struct Swan;

impl Bird for Duck {
    fn fly222(&self) {
        let a = "asdfsdfsdafsdf";
        println!("duck duck11111111111111111111111111111111");
    }
    fn fly(&self) { println!("duck duck"); }
}

impl Bird for Swan {
    fn fly222(&self) { println!("swan swan"); }
    fn fly(&self) { println!("swan swan"); }
}

// 参数是 trait object 类型,p 是一个胖指针
fn print_traitobject(p: &dyn Bird) {
    // 使用transmute执行强制类型转换,把变量p的内部数据取出来
    let (data, vtable): (usize, *const usize) = unsafe { mem::transmute(p) };
    println!("TraitObject [data:{}, vtable:{:p}]", data, vtable);
    unsafe {
        // 打印出指针 v 指向的内存区间的值
        println!("data in vtable [{:x}, {}, {}, 实现的第1个函数的地址:{}， 实现的第2个函数的地址：{}]",
                 *vtable, *vtable.offset(1), *vtable.offset(2), *vtable.offset(3), *vtable.offset(4)); // 解释这4个数据的含义
    }
}

fn main() {
    let duck = Duck { get: 1, get2: 321, get3: 3 };
    let p_duck = &duck;
    let p_bird = p_duck as &dyn Bird;
    println!("Size of &duck: {}, Size of &dyn p_bird: {}", mem::size_of_val(&p_duck), mem::size_of_val(&p_bird));
    let duck_fly: usize = Duck::fly as usize;
    let swan_fly: usize = Swan::fly as usize;
    println!("duck obj locate at = {:p}", &duck);
    let tmp = &duck;
    println!("&duck obj locate at = {:p}", &tmp);
    println!("&duck obj locate at = {:p}", &tmp);
    let tmp = &duck;
    println!("&duck obj locate at = {:p}", &tmp);
    println!("&duck obj locate at = {:p}, {:p}", &tmp, tmp);
    println!("Duck::fly locate {}", duck_fly);
    println!("Duck::fly222 locate at {}", Duck::fly222 as usize);
    println!("Swan::fly locate at {}", swan_fly);
    println!("Swan::fly222 locate at {}", Swan::fly222 as usize);
    print_traitobject(p_bird);
    let swan = Swan;
    print_traitobject(&swan as &dyn Bird);
    let a = 1;
    println!("a本身大小{}， a的引用的大小{}", mem::size_of_val(&a), mem::size_of_val(&&a));
}