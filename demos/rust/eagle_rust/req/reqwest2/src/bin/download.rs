use indicatif::{ProgressBar, ProgressState, ProgressStyle};
use reqwest::header::HeaderMap;
use std::fmt::Write;
use tokio::fs::File;
use tokio::io::{AsyncSeekExt, AsyncWriteExt};
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    let url = "http://58.ddooo.com/Tth3Chs.exe?key=9096cb20df56d0bdb3403adf1c284eec&uskey=d6374a3b2e9060cc117be19221a3a598";
    let url_mod = reqwest::Url::parse(url)?;
    let path = url_mod.path();
    let filename = *path.split("/").collect::<Vec<&str>>().last().unwrap();
    println!("filename = {}", filename);

    // 检查本地文件是否存在
    let metadata = tokio::fs::metadata(&filename).await;

    let client = reqwest::Client::new();

    // 创建一个 HeaderMap，并添加自定义的 header
    let mut headers = reqwest::header::HeaderMap::new();

    // 如果文件已存在，设置请求字节偏移量为已下载的文件大小
    if let Ok(metadata) = metadata {
        let range_value = format!("bytes={}-", metadata.len());
        headers.insert(reqwest::header::RANGE, range_value.parse()?);
    }

    let mut response = client.get(url).headers(headers).send().await?;
    let mut file = tokio::fs::OpenOptions::new()
        .create(true)
        .append(true)
        .open(filename)
        .await?;

    let total_size = response.content_length().unwrap_or(0);
    println!("total_size = {}", total_size);
    let mut downloaded_size = 0u64;
    let pb = ProgressBar::new(total_size);

    pb.set_style(ProgressStyle::with_template("{spinner:.green} [{elapsed_precise}] [{wide_bar:.cyan/blue}] {bytes}/{total_bytes} ({eta})")
        .unwrap()
        // .with_key("eta", |state: &ProgressState, w: &mut dyn Write| write!(w, "{:.1}s", state.eta().as_secs_f64()).unwrap())
        .progress_chars("#>-"));

    pb.set_position(downloaded_size);
    // let chunk =  response.chunk().await;
    // 获取响应头
    let headers: &HeaderMap = response.headers();
    let content_range = response
        .headers()
        .get("Content-Range");
    let content_length = headers.get("content-length").unwrap().to_str()?;
    println!("content_length = {}", content_length);
    if content_range.is_none() && content_length.eq("3846"){
        return Ok(());
    }
    // 遍历并打印所有头字段信息
    for (name, value) in headers.iter() {
        println!("{}: {:?}", name, value);
    }
    // if response.chunk().await.is_err() {
    //     let a = response.chunk().await.
    //     return ;
    // }
    while let Some(chunk) = response.chunk().await.unwrap() {
        file.write_all(&chunk).await?;
        downloaded_size += chunk.len() as u64;
        let msg = format!("Downloaded: {} / {} bytes", downloaded_size, total_size);
        pb.set_message(msg);
        pb.set_position(downloaded_size);

        // 每次写入数据后，同步文件，确保数据已写入磁盘
        file.sync_all().await?;
    }

    // pb.finish_with_message("Download complete");

    Ok(())
}
