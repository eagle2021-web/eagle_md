use std::error::Error;
use reqwest;
fn main() -> Result<(), Box<dyn Error>>{    //-  Box<dyn std::error::Error> 表示一个trait对象，我们会在8.3节中介绍。
    let url = "https://www.baidu.com";
    let mut response = reqwest::get(url)?;
    let content = response.text()?;
    print!("{}", content);
    Ok(())
}