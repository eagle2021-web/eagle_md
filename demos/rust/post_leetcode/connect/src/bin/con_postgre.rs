use postgres::{Client, NoTls};

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // 建立数据库连接
    let mut client = Client::connect("postgres://postgres:1@localhost:5432/postgres", NoTls)?;

    // 执行 SQL 查询
    for row in client.query("SELECT id, name FROM student", &[])? {
        let id: i32 = row.get(0);
        let name: String = row.get(1);
        println!("id: {}, name: {}", id, name);
    }

    Ok(())
}


#[cfg(test)]
mod test_async {

    #[test]
    fn test_hello_world_sync() {
        use tokio::time::{sleep, Duration};
        use tokio::runtime::Runtime;
        // 定义一个返回 "Hello, world!" 的异步函数
        async fn hello_world() -> String {
            let _ = sleep(Duration::from_secs(1)).await;
            "Hello, world!".to_string()
        }
        // 使用同步方式测试异步函数结果是否正确
        let rt = Runtime::new().unwrap();
        let res = rt.block_on(hello_world());
        assert_eq!(res, "Hello, world!".to_string());
    }

    #[tokio::test]
    async fn test_spawn() {
        use std::sync::mpsc::channel;
        use tokio::spawn;
        use tokio::task::block_in_place;
        use std::thread;

        let (tx, rx) = channel();
        thread::spawn(move || {
            for i in 0..10 {
                tx.send(i).unwrap();
            }
        });
        let mut i = 0;
        while let Ok(r) = rx.recv() {
            println!("received {}", r);
            i += r;
        }
        assert_eq!(45, i);
    }
}