use generator::Gn;

fn fibonacci() -> Gn<u64> {
    Gn::new_scoped(move |mut s| {
        let (mut a, mut b) = (0, 1);
        loop {
            s.suspend(a);
            let next = a + b;
            a = b;
            b = next;
        }
    })
}

fn main() {
    let fib_generator = fibonacci();

    for num in fib_generator.into_iter().take(10) {
        println!("{}", num);
    }
}