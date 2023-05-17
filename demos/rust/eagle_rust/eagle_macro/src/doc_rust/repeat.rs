#![allow(unused_macros)]
// `find_min!` will calculate the minimum of any number of arguments.
macro_rules! find_min {
    // Base case:
    ($x:expr) => ($x);
    // `$x` followed by at least one `$y,`
    ($x:expr, $($y:expr),+) => (
        // Call `find_min!` on the tail `$y`
        std::cmp::min($x, find_min!($($y),+))
    )
}

#[cfg(test)]
mod tests {

    #[test]
    fn test_find_min() {
        println!("{}", find_min!(1));
        println!("{}", find_min!(1 + 2, 2));
        println!("{}", find_min!(5, 2 * 3, 4));
    }
}
