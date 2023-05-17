use serde_json;
use reqwest;
use std::{env, fs};

#[tokio::main]
async fn main() -> Result<(), reqwest::Error> {
    let url = match env::args().nth(1) {
        None => "https://jsonplaceholder.typicode.com/posts".to_string(),
        Some(s) => s
    };
    let js: serde_json::Value = reqwest::Client::builder()
        .danger_accept_invalid_certs(true)
        .no_proxy()
        .build()
        .unwrap()
        .get(url)
        .send()
        .await
        .unwrap()
        .json()
        .await
        .unwrap();
    let output_json_path = match env::args().nth(2) {
        None => {
            println!("{}", "输出json数据在tmp.json");
            "./tmp.json".to_string()
        }
        Some(s) => s
    };
    fs::write(&output_json_path, js.to_string())
        .expect("Failed to write!");
    assert!(js.is_array());
    Ok(())
}


#[cfg(test)]
mod tests {
    use std::fs;
    use reqwest;
    use serde_json::json;

    #[test]
    fn test_1a() {
        let s = r#"{"query":"\n    query recentAcSubmissions($userSlug: String!) {\n  recentACSubmissions(userSlug: $userSlug) {\n    submissionId\n    submitTime\n    question {\n      translatedTitle\n      titleSlug\n      questionFrontendId\n    }\n  }\n}\n    ","variables":{"userSlug":"ssshards"}}"#;
        let mut s:serde_json::Value = serde_json::from_str(s).unwrap();
        s["variables"]["userSlug"] = json!("eagle");
        println!("s = {:?}", s);
    }
    #[actix_rt::test]
    async fn test_a() {
        let s = r#"{"query":"\n    query recentAcSubmissions($userSlug: String!) {\n  recentACSubmissions(userSlug: $userSlug) {\n    submissionId\n    submitTime\n    question {\n      translatedTitle\n      titleSlug\n      questionFrontendId\n    }\n  }\n}\n    ","variables":{"userSlug":"ssshards"}}"#;
        let s:serde_json::Value = serde_json::from_str(s).unwrap();
        println!("s = {:?}", s);
        let url = "https://leetcode.cn/graphql/noj-go/";
        let text:serde_json::Value = reqwest::Client::builder()
            .danger_accept_invalid_certs(true)
            .no_proxy()
            .build()
            .unwrap()
            .post(url)
            .json(&s)
            .send()
            .await
            .unwrap()
            .json()
            .await
            .unwrap();
        // println!("{:?}", text);
        fs::write("tmp.json", text.to_string()).expect("Failed to write");
    }

    #[actix_rt::test]
    async fn test_b() {
        let u = "https://leetcode.cn/problems/binary-search/";
        let text = reqwest::Client::builder()
            .danger_accept_invalid_certs(true)
            .no_proxy()
            .build()
            .unwrap()
            .get(u)
            .send()
            .await
            .unwrap()
            .text()
            .await
            .unwrap();
        fs::write("tmp.html", text).expect("Failed to write");
    }
}