use std::fs::File;
use std::io::{self, Read};
use std::path::Path;
use reqwest::Client;
use tokio::fs::File as AsyncFile;
use tokio::io::{AsyncReadExt, AsyncWriteExt};

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    // 设置要下载的文件 URL
    let url = "https://cdn-lfs.huggingface.co/repos/46/9e/469e398eafc56499bdfe55472ce18a7296c633c25ee25b0ca695b7581d2d1e46/2c52afae51f496879ae2164f12c4f9b879cb9ee80bd32a7a4a485b3994e88720?response-content-disposition=attachment%3B+filename*%3DUTF-8%27%27pytorch_model-00001-of-00007.bin%3B+filename%3D%22pytorch_model-00001-of-00007.bin%22%3B&response-content-type=application%2Foctet-stream&Expires=1694271340&Policy=eyJTdGF0ZW1lbnQiOlt7IkNvbmRpdGlvbiI6eyJEYXRlTGVzc1RoYW4iOnsiQVdTOkVwb2NoVGltZSI6MTY5NDI3MTM0MH19LCJSZXNvdXJjZSI6Imh0dHBzOi8vY2RuLWxmcy5odWdnaW5nZmFjZS5jby9yZXBvcy80Ni85ZS80NjllMzk4ZWFmYzU2NDk5YmRmZTU1NDcyY2UxOGE3Mjk2YzYzM2MyNWVlMjViMGNhNjk1Yjc1ODFkMmQxZTQ2LzJjNTJhZmFlNTFmNDk2ODc5YWUyMTY0ZjEyYzRmOWI4NzljYjllZTgwYmQzMmE3YTRhNDg1YjM5OTRlODg3MjA%7EcmVzcG9uc2UtY29udGVudC1kaXNwb3NpdGlvbj0qJnJlc3BvbnNlLWNvbnRlbnQtdHlwZT0qIn1dfQ__&Signature=p%7EYhpec4SQgUngaYPlyNzVWtafQ8WwU03Xpupz3cY-OQ9tgQ6nqDaKeFOT2fhktm3%7Ekgn-6lMag7YJBUgYdaXUPXoi%7EX%7EkOU9JmY4RNTIgM1ucvlENpn2v5kbUCAx76TxyY%7Eqz6PaiAqRtIqeiRAAUNdZyLO-Zuo0iFwr5SE8XDBFHj-rIVKdG06lcm5QttE%7EQQhmLswNupmCuwGGS%7E9GlOg%7Ei8Q7KmEhFkDtSLxuuRtAkrqd%7EPUDAjA9TgAsCn6AaS92Z5zi07GZRKePHxp5HvBEojsXcTy8JLzPsWbI%7EmRAupol6TzD6o-clprb5jNvCRwC2YS33VrcDkGKaHnoQ__&Key-Pair-Id=KVTP0A1DKRTAX";

    // 创建一个 HTTP 客户端
    let client = Client::new();
    let mut headers = reqwest::header::HeaderMap::new();

    // 构建一个 Cookie，将它添加到请求头中
    // let cookie = reqwest::header::HeaderValue::from_str("")?;
    // headers.insert(reqwest::header::COOKIE, cookie);

    // 发送带有 Cookie 的 HEAD 请求获取文件大小
    let response = client.head(url).headers(headers).send().await?;

    // 检查响应状态码
    if response.status().is_success() {
        let headers = response.headers();
        let content_len = headers.get("content-length").unwrap()
            .to_str().unwrap().parse::<u64>()
            .unwrap();
        println!("{}", content_len);
        for x in response.headers() {
            println!("{}:{:?}",x.0, x.1);
        }
        println!("1111111");
        // 从响应头中获取文件大小
        // let content_length = response.content_length();
        // println!("content_length = {}", content_length);
        // 设置每块文件的大小为 1MB
        // let chunk_size = 1 * 1024 * 1024; // 1MB
        //
        // // 计算分块的数量
        // let num_chunks = (content_length as f64 / chunk_size as f64).ceil() as usize;
        //
        // // 创建并打开文件
        // let file_path = Path::new("path/to/save/pytorch_model-00001-of-00007.bin");
        // let mut file = AsyncFile::create(file_path).await?;
        //
        // // 循环下载每个分块
        // for chunk_index in 0..num_chunks {
        //     // 设置分块的范围
        //     let start_byte = chunk_index * chunk_size;
        //     let end_byte = (start_byte + chunk_size - 1).min(content_length - 1);
        //
        //     // 构建分块下载的请求
        //     let request = client.get(url).header("Range", format!("bytes={}-{}", start_byte, end_byte));
        //
        //     // 发送分块下载的请求
        //     let mut response = request.send().await?;
        //
        //     // 检查响应状态码
        //     if response.status().is_success() {
        //         // 读取分块的字节内容
        //         let mut buffer = vec![0; chunk_size as usize];
        //         response.read_exact(&mut buffer).await?;
        //
        //         // 将分块写入文件的对应位置
        //         file.seek(io::SeekFrom::Start(start_byte)).await?;
        //         file.write_all(&buffer[..(end_byte - start_byte + 1) as usize]).await?;
        //     } else {
        //         println!("请求失败，分块索引：{}，状态码：{}", chunk_index, response.status());
        //     }
        // }

        println!("文件下载完成！");
    } else {
        println!("请求失败：{}", response.status());
    }

    Ok(())
}