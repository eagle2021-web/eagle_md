#[path = "doc_rust/mod.rs"]
pub mod doc_rust;

#[cfg(test)]
mod tests {
    use crate::{print_result, say_hello};
    use crate::test;

    #[test]
    fn it_works() {
        let result = 2 + 2;
        assert_eq!(result, 4);
    }

    #[test]
    fn test_say_helle() {
        say_hello!();
    }

    #[test]
    fn test_test() {
        test!(1 == 1; and 2 == 2);
    }

    #[test]
    fn allow_unused_macro() {
        print_result!(1);
    }
}
