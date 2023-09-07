use std::boxed::Box;
extern crate alloc;
fn main() {
    // cargo expand --bin expand
    let sum = vec![11, 12];
    let a = Box::new(232);
    let sum = <[_]>::into_vec(::alloc::boxed::Box::new([11, 12]));
    let sum = Box::new([1212, 12]).to_vec();
    let sum = vec![10; 20];
    let sum = ::alloc::vec::from_elem(10, 20);
}