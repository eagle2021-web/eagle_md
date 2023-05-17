#![allow(unused_mut)]
fn main() {
    let mut s = String::from("hello");
    // let a = &s;
    let _word = first_word(&s);
    // s.clear();
    // println!("{}", a);
}

fn first_word(s: &str) -> &str{
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }

    &s[..]
}