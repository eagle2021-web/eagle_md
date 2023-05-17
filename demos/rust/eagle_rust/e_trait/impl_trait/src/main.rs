fn main() {
    println!("Hello, world!");
}
#[cfg(test)]
mod test {
    #[test]
    fn test_foo() {
        fn foo(n : u32) -> impl Iterator<Item=u32> {
            (0..n).map(|x|x * 100)
        }
        let a = foo(10).sum::<u32>();
        assert_eq!(a, 45 * 100);
    }

    #[test]
    fn test_multiply() {
        fn multiply(m: i32) -> impl Fn(i32)->i32 {
            move |x| x * m
        }
        let a = multiply(10)(2);
        assert_eq!(a, 20);
    }
}