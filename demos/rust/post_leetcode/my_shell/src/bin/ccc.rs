use druid_shell::{Application};

fn main() {
    let mut clipboard = Application::new().unwrap().clipboard();
    if let Some(contents) = clipboard.get_string() {
        let s = contents.as_str().replace("\\", "/");
        println!("{}   已经在粘贴板", s);
        clipboard.put_string(s);
    }
}