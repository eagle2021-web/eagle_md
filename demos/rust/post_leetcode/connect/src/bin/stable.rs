use reqwest::header::{HeaderMap, HeaderValue, ACCEPT, USER_AGENT, REFERER};
use reqwest::Client;
use std::error::Error;
use std::io::Write;
use serde_json::json;
use connect::stable_value;
#[tokio::main]
async fn main() ->  Result<(), Box<dyn std::error::Error>> {
    let data = stable_value.clone();
    println!("{:#?}", data);
    let mut file = std::fs::File::create("tmp2.json")?;

    file.write_all(data.to_string().as_bytes())?;
    let url = "http://127.0.0.1:7860/run/predict/";


    let mut headers = HeaderMap::new();
    headers.insert(USER_AGENT, HeaderValue::from_static("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0"));
    headers.insert(ACCEPT, HeaderValue::from_static("*/*"));
    headers.insert(REFERER, HeaderValue::from_static("http://127.0.0.1:7860/?__theme=dark"));

    let client = Client::new();
    println!("wating!");
    let response = client.post(url)
        .headers(headers)
        .body(data.to_string())
        .send()
        .await?;
    let js: serde_json::Value = response.json().await.unwrap();
    println!("{:?}", js);
    let mut file = std::fs::File::create("tmp.json")?;

    file.write_all(js.to_string().as_bytes())?;
    Ok(())
}