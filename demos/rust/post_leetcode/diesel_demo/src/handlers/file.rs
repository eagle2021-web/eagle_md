use std::fs;
use std::io::Write;
use actix_multipart::Multipart;
use actix_web::{web, App, Error, HttpResponse, HttpServer, patch, error};
use actix_web::http::header;
use futures::{StreamExt, TryStreamExt};
use uuid::Uuid;

pub async fn handle_upload(mut payload: Multipart) -> Result<HttpResponse, actix_web::Error> {
    // let a = payload.try_next().await;
    // println!("ok = {}", a.is_ok());
    // while let Some(field) = payload.try_next().await? {
    //     let a = payload.try_next().await;
    //     println!("ok = {}", "ok");
    // }
    while let Ok(Some(mut field)) = payload.try_next().await {
        // let content_type = field.content_disposition().to_string();
        // println!("a = {}", content_type);
        // 获取 Content-Disposition 请求头
        let content_disposition = field.headers().get(header::CONTENT_DISPOSITION);
        // 如果请求头中包含 Content-Disposition
        if let Some(content_disposition) = content_disposition {
            // 将请求头中的值解析为字符串，并提取文件名
            println!("a = {:?}", content_disposition);
            let s = String::from_utf8(
                content_disposition.as_bytes().to_vec()).unwrap();
            println!("a = {:?}", &s);

            let re = regex::Regex::new(r#"filename=".*?""#).unwrap();
            if let Some(filename) = re.find(&s[..]) {
                println!("match = {:?}", filename);
                let filename = &s[filename.start()+10 .. filename.end()-1];
                println!("filename: {:?}", filename);

                // // 保存文件到本地
                let filepath = format!("./{}", filename);
                let mut f = fs::File::create(filepath)?;
                while let Some(chunk) = field.next().await {
                    println!("{}", "writing!");
                    let data = chunk?;
                    let a = f.write_all(&data)?;
                }

                // return Ok(HttpResponse::Ok().json("上传成功"));
            }

        }
        // let filename = match field.content_disposition() {
        //     Some(disposition) => {
        //         let mut parts = disposition.parameters.iter();
        //         while let Some(part) = parts.next() {
        //             if part.0.as_str() == "filename" {
        //                 break;
        //             }
        //         }
        //         parts.next().unwrap_or("").to_string()
        //     }
        //     None => return Err(error::ErrorBadRequest("Bad request")),
        // };
        //
        // // 处理文件数据
        // while let Some(chunk) = field.next().await {
        //     let data = chunk.unwrap();
        //     println!("Chunk: {:?}", data);
        //     // 将数据流写入磁盘或云存储
        // }
    }

    Ok(HttpResponse::Ok().body("Upload success"))
}