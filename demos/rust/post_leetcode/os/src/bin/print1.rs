use std::process::Command;
use std::time::Duration;

fn main() {
    // 使用 Command::new() 函数创建一个新的命令对象
    let mut cmd = Command::new("dir");

    // 设置剩余的命令参数
    cmd.arg("./");
    // 使用 spawn() 函数启动子进程，并在后台运行
    if let Ok(child) = cmd.spawn() {
        // 子进程已经成功启动，在此之后，主线程将立即退出
        // let mut count = 0;
        // let timeout = Duration::from_secs(600);
        // let start_time = std::time::Instant::now();
        // loop {
        //     if start_time.elapsed() > timeout || count >= 600 {
        //         // 如果超时条件满足或计数器达到预设值，向主线程发送结束信号并退出循环
        //         break;
        //     }
        //     println!("Current time: {}", count);
        //     count += 1;
        //     std::thread::sleep(std::time::Duration::from_secs(1));
        // }
        // println!("Command is running in the background.");
    } else {
        // 启动子进程失败，打印错误信息并退出程序
        eprintln!("Failed to start command.");
        std::process::exit(1);
    }
    println!("ok{}", "dfa");
}