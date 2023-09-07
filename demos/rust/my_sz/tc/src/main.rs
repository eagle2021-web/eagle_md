use notify_rust::{Hint, Notification};
use std::time::Duration;

fn main() {
    let duration = Duration::from_secs(60 *40);
    std::thread::sleep(duration);
    // 设置定时器的时间，这里是 60 秒
    let mut m = Notification::new();
        m.summary("Category:email")
        .body("This has nothing to do with emails.\nIt should not go away until you acknowledge it.")
        .icon("thunderbird")
        .appname("thunderbird")
        // .hint(Hint::Category("email".to_owned()))
        // .hint(Hint::Resident(true)) // this is not supported by all implementations
        .timeout(0) // this however is
        .show().unwrap();

}