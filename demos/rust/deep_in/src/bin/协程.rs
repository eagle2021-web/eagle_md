use tokio::time::{sleep, Duration};

async fn coroutine(id: u32) {
    for i in 0..5 {
        println!("Coroutine {} count: {}", id, i);
        sleep(Duration::from_secs(1)).await;
    }
}

#[tokio::main]
async fn main() {
    let coroutines = vec![
        tokio::spawn(coroutine(1)),
        tokio::spawn(coroutine(2)),
        tokio::spawn(coroutine(3)),
    ];

    for coroutine in coroutines {
        coroutine.await.unwrap();
    }
}