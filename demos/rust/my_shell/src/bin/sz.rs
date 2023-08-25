use std::str::FromStr;
use druid_shell::{Application};

fn main() {
    let mut clipboard = Application::new().unwrap().clipboard();
    if let Some(contents) = clipboard.get_string() {
        println!("{}", contents);
        println!("========");
        let lines: Vec<String> = contents.lines()
            .map(|line| {
                let trimmed_line = line.trim_start();
                if let Some(index) = trimmed_line.find(' ') {
                    let (_, rest) = trimmed_line.split_at(index);
                    rest.trim_start().to_string()
                } else {
                    trimmed_line.to_string()
                }
            })
            .map(|line| line.replace("⇽---", "//"))
            .filter(|v| {
                let a = i32::from_str(v);
                return a.is_err();
            })
            .collect();
        let result = lines.join("\n");
        clipboard.put_string(result);
    }
}