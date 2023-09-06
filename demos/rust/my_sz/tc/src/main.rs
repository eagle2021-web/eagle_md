use notify_rust::{Hint, Notification};
use std::time::Duration;

fn main() {
    let duration = Duration::from_secs(1); // 设置定时器的时间，这里是 60 秒
    let mut m = Notification::new();
        m.summary("Category:email")
        .body("This has nothing to do with emails.\nIt should not go away until you acknowledge it.")
        .icon("thunderbird")
        .appname("thunderbird")
        // .hint(Hint::Category("email".to_owned()))
        // .hint(Hint::Resident(true)) // this is not supported by all implementations
        .timeout(0) // this however is
        .show().unwrap();
    loop {
        std::thread::sleep(duration);

        let mut notification = Notification::new();

            notification.summary("提示信息")
            .body("时间到了！")
            .icon("dialog-information")
            .appname("定时器");

        // 弹出通知消息框
        match notification.show() {
            Ok(_) => {
                println!("用户点击了通知框");
                // 用户点击了通知框，继续执行下一次定时
            }
            Err(error) => {
                println!("出现了错误：{}", error);
                // 出现了错误，可以进行错误处理
            }
        }
    }
}