use std::mem;

static B1: [u8; 10] = [99, 97, 114, 114, 121, 116, 111, 119, 101, 108];
fn main(){
    let s = String::from("hello");

    let mut s = mem::ManuallyDrop::new(s);

    let ptr = s.as_mut_ptr();
    let len = s.len();
    let capacity = s.capacity();

    unsafe {
        let s = String::from_raw_parts(ptr, len, capacity);

        assert_eq!(String::from("hello"), s);
    }
}

// fn main() {
//     let a = 42;
//     // let mut  b: String;
//     let c: Cow<str>;
//
//     unsafe {
//         println!("{} ", 1);
//         // let b_ptr = &B as *const u8 as *mut u8;
//         // b = String::from_raw_parts(b_ptr, 0, 1);
//         // b = "sss".to_string();
//         let c_ptr = C.as_ptr() as *const c_char;
//         c = CStr::from_ptr(c_ptr).to_string_lossy();
//         println!("a: {}, c {}, c:", a, c);
//     }
//     // drop(c);
//     // println!("a: {}, b: {}, c:", a, b);
// }