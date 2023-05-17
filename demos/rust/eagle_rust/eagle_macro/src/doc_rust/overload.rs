// `test!` will compare `$left` and `$right`
// in different ways depending on how you invoke it:
#[macro_export]
macro_rules! test {
    // Arguments don't need to be separated by a comma.
    // Any template can be used!
    ($left:expr; and $right:expr) => {
        println!("{:?} and {:?} is {:?}",
                 stringify!($left),
                 stringify!($right),
                 $left && $right)
    };
    // ^ each arm must end with a semicolon.
    ($left:expr; or $right:expr) => {
        println!("{:?} or {:?} is {:?}",
                 stringify!($left),
                 stringify!($right),
                 $left || $right)
    };
}

#[cfg(test)]
mod tests {

    #[test]
    fn test_test() {
        test!(1i32 + 1 == 2i32; and 2i32 * 2 == 4i32 && 1 == 1 && 2 == 1);
        test!(true; or false);
    }
}
