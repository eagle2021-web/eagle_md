#![allow(unused)]
extern crate proc_macro_lib;

use proc_macro_lib::make_answer;
use proc_macro_lib::AnswerFn;
use proc_macro_lib::HelperAttr;
use proc_macro_lib::show_streams;
use proc_macro_lib::HelloMacro;

make_answer!();

#[derive(AnswerFn)]
struct Struct;

#[derive(HelperAttr, Debug)]
#[show_streams]
struct Struct2 {
    #[helper] field: ()
}
// Example: Basic function
#[show_streams]
fn invoke1() {}
// out: attr: ""
// out: item: "fn invoke1() { }"

// Example: Attribute with input
#[show_streams(bar)]
fn invoke2() {}
// out: attr: "bar"
// out: item: "fn invoke2() {}"

// Example: Multiple tokens in the input
#[show_streams(multiple => tokens)]
fn invoke3() {}
// out: attr: "multiple => tokens"
// out: item: "fn invoke3() {}"

// Example:
#[show_streams { delimiters }]
fn invoke4() {}
// out: attr: "delimiters"
// out: item: "fn invoke4() {}"

pub trait HelloMacro {
    fn hello_macro();
}

#[derive(HelloMacro)]
struct Pancakes2222;

fn main() {
    println!("{}", answer());

    assert_eq!(43, answer43());
    let s = Struct2{ field: () };
    println!("s = {:?}", s);

    let a = invoke3();
    Pancakes2222::hello_macro();
}