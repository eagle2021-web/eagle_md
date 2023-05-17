extern crate scoped_threadpool;

use scoped_threadpool::Pool;

fn main() {
    let mut pool = Pool::new(4);
    let mut vec = vec![0, 1, 2, 3, 4, 5, 6, 7];
    let (tx, rx) = std::sync::mpsc::channel();
    pool.scoped(|scope| {
        for e in &mut vec {
            let tx = tx.clone();

            scope.execute(move || {
                *e += 1;
                tx.send(*e).unwrap();
            });
        }
    });
    let a:i32 = rx.try_iter().collect::<Vec<i32>>().iter().sum();
    println!("{a}");
    println!("{:?}", vec);
    assert_eq!(a, 36);
}