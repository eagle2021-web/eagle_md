use num::complex::Complex;

//  使用use关键字把Complex类型导入当前的局部作用域。
fn main() {
    let a = Complex { re: 2.1, im: -1.2 };    //  所有Rust的类型都有其字面量的语法。
    let b = Complex::new(11.1, 22.22);    //  大多数的类型都实现了静态方法new()。
    let result = a + b;
    println!("{} +{}i", result.re, result.im)    //  使用点操作符来访问字段。
}