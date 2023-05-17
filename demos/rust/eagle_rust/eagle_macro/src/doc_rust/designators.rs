#![allow(dead_code)]
macro_rules! create_function {
    // This macro takes an argument of designator `ident` and
    // creates a function named `$func_name`.
    // The `ident` designator is used for variable/function names.
    ($func_name:ident) => {
        fn $func_name() {
            // The `stringify!` macro converts an `ident` into a string.
            println!("You called {:?}()",
                     stringify!($func_name));
        }
    };
}

// Create functions named `foo` and `bar` with the above macro.
create_function!(foo);
create_function!(bar);

#[macro_export]
macro_rules! print_result {
    // This macro takes an expression of type `expr` and prints
    // it as a string along with its result.
    // The `expr` designator is used for expressions.
    ($express:expr) => {
        // `stringify!` will convert the expression *as it is* into a string.
        println!("{:?} = {:?}",
                 stringify!($express),
                 $express);
    };
}

#[cfg(test)]
mod tests {
    use crate::doc_rust::designators::{bar, foo};

    #[test]
    fn test_a() {
        foo();
        bar();

        print_result!(1u32 + 1);

        // Recall that blocks are expressions too!
        print_result!({
            let x = 1u32;
            x * x + 2 * x - 1
        });

        #[derive(Debug)]
        #[allow(dead_code)]
        struct Student {
            name: String,
        }
        print_result!(
            Student{
                name: "eagle".to_string()
            }
        );
    }
}