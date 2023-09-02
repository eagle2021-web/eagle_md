use std::time::Duration;
use tokio::task;
use tokio::time::sleep;

async fn square(n: i32) -> i32 {
    println!("Calculating square of {}", n);
    sleep(Duration::from_millis(200)).await;
    task::yield_now().await;  // 模拟异步操作
    n * n
}

#[tokio::main]
async fn main() {
    let task1 = task::spawn(square(5));
    let task2 = task::spawn(square(10));
    let task3 = task::spawn(square(15));

    let result1 = task1.await.unwrap();
    let result2 = task2.await.unwrap();
    let result3 = task3.await.unwrap();

    println!("Result 1: {}", result1);
    println!("Result 2: {}", result2);
    println!("Result 3: {}", result3);
    println!("Result 3: {}", "end");
}