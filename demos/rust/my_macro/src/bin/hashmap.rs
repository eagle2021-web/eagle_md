#![allow(unused)]
use std::collections::HashMap;
use std::mem::{size_of, size_of_val};
macro_rules! hashmap {
    ($( $key: expr => $val: expr ),*) => {{
        let mut map = ::std::collections::HashMap::new();
        $( map.insert($key, $val); )*
        map
    }}
}

fn main() {
    let s = "dasf";
    println!("P{}", s.len());
    println!("P{:p}", &s);
    println!("P{}", std::mem::size_of_val(&s));
    println!("P{}", std::mem::size_of_val(s));
}