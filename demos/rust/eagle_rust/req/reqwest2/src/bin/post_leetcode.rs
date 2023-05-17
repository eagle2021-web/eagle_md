
use reqwest::Client;
use serde_json::json;
use serde::Serialize;
use serde::Deserialize;
#[derive(Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase")]
struct Node {
    created_at: String,
    question: Question,
    slug: String,
    status: String,
    title: String,
    topic: Topic,
    upvote_count: i32,
}
#[derive(Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase")]
struct Question {
    question_frontend_id: String,
    title_slug: String,
    translated_title: String,
}
#[derive(Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase")]
struct Topic {
    view_count: i32,
}
#[derive(Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase")]
struct BigNode {
    node: Node
}
#[derive(Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase")]
struct SolutionArticles {
    // edges: Vec<BigNode>,
    page_info: PageInfo
}
#[derive(Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase")]
struct Data {
    solution_articles: SolutionArticles,
}
#[derive(Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase")]
struct PageInfo {
    has_next_page: bool
}
#[derive(Debug, Serialize, Deserialize)]
struct MyStruct {
    data: Option<Data>
}
#[tokio::main]
async fn main() ->  Result<(), Box<dyn std::error::Error>> {
    let client = Client::new();
    let url = "https://leetcode.cn/graphql/";
    let body = json!({"query":"\n    query profileSolutionArticles($userSlug: String!, $skip: Int, $first: Int) {\n  solutionArticles(userSlug: $userSlug, skip: $skip, first: $first) {\n    pageInfo {\n      hasNextPage\n    }\n    edges {\n      node {\n        title\n        slug\n        createdAt\n        status\n        question {\n          titleSlug\n          translatedTitle\n          questionFrontendId\n        }\n        upvoteCount\n        topic {\n          viewCount\n        }\n      }\n    }\n  }\n}\n    ","variables":{"userSlug":"ac_oier","skip":0,"first":15},"operationName":"profileSolutionArticles"}
);
    let request = client.post(url)
        .body(serde_json::to_string(&body)?)
        .header("Host", "leetcode.cn")
        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/112.0")
        .header("Accept", "*/*")
        .header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
        .header("Accept-Encoding", "gzip, deflate, br")
        .header("Content-Type", "application/json")
        .header("x-csrftoken", "OtnJedJ5VArXwSDzFLvPzPcZHSRqnrkSUUsfBK2W6dsPLP0ZLejdS1ueLoQSz1BB")
        .header("authorization", "")
        .header("random-uuid", "c95aad60-1772-c62c-710c-a79010272db6")
        .header("Origin", "https://leetcode.cn")
        .header("Connection", "keep-alive")
        .header("Referer", "https://leetcode.cn/u/ac_oier/")
        .header("Sec-Fetch-Dest", "empty")
        .header("Sec-Fetch-Mode", "cors")
        .header("Sec-Fetch-Site", "same-origin")
        // .header("Cookie", "csrftoken=OtnJedJ5VArXwSDzFLvPzPcZHSRqnrkSUUsfBK2W6dsPLP0ZLejdS1ueLoQSz1BB; Hm_lvt_f0faad39bcf8471e3ab3ef70125152c3=1681743922; Hm_lpvt_f0faad39bcf8471e3ab3ef70125152c3=1681744620; _ga_PDVPZYN3CW=GS1.1.1681743922.1.1.1681746152.0.0.0; _ga=GA1.2.1449657506.1681743922; gr_user_id=e6de0864-f2a0-4b62-883e-1362e7279fb9; a2873925c34ecbd2_gr_session_id_7525963e-c9a4-4b17-b6cc-e9520795155c=true; a2873925c34ecbd2_gr_session_id=7525963e-c9a4-4b17-b6cc-e9520795155c; _gid=GA1.2.2012201347.1681743923; _bl_uid=v3l7agp8kL2y5nwwysyn205vOyny; LEETCODE_SESSION=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiNTE4Nzc5NyIsIl9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9oYXNoIjoiYTE2NDBlNWMzNDM0ZWI4YmYwYjhjMDM1YTYwYmUzYThkNjZlZGY3MDA0MGUzMzRhODVlODA2NGNkMTU0ZjBiMyIsImlkIjo1MTg3Nzk3LCJlbWFpbCI6IiIsInVzZXJuYW1lIjoiZnVubnktYWxiYXR0YW5paWNzIiwidXNlcl9zbHVnIjoiZnVubnktYWxiYXR0YW5paWNzIiwiYXZhdGFyIjoiaHR0cHM6Ly9hc3NldHMubGVldGNvZGUuY24vYWxpeXVuLWxjLXVwbG9hZC9kZWZhdWx0X2F2YXRhci5wbmciLCJwaG9uZV92ZXJpZmllZCI6dHJ1ZSwiX3RpbWVzdGFtcCI6MTY4MTc0Mzk1Ni4yNDc3NjEsImV4cGlyZWRfdGltZV8iOjE2ODQyNjM2MDAsInZlcnNpb25fa2V5XyI6MiwibGF0ZXN0X3RpbWVzdGFtcF8iOjE2ODE3NDQ2MTl9.LSxfWU_n4FCQfkpdKhKzoWd-pBcFtvc-EEBUaSt3p8s; a2873925c34ecbd2_gr_last_sent_sid_with_cs1=7525963e-c9a4-4b17-b6cc-e9520795155c; a2873925c34ecbd2_gr_last_sent_cs1=funny-albattaniics; NEW_PROBLEMLIST_PAGE=1; a2873925c34ecbd2_gr_cs1=funny-albattaniics");
        .header("Cookie", r#"gr_user_id=1a2397cf-cf1d-4173-9fe7-09e566ea6085; _bl_uid=ahlz8gy5fRh9t5okmv3vrLpde1OX; csrftoken=0h1ZPHod3GV0FSdPHuubnrGt6UB1hOUCNTy8MTnX3lDivQvlqpHq1MBpb3puSQOH; a2873925c34ecbd2_gr_last_sent_cs1=funny-albattaniics; _gid=GA1.2.918915031.1681919995; a2873925c34ecbd2_gr_session_id=cdcb1d60-06a7-4723-baa3-fccf7e767d80; a2873925c34ecbd2_gr_last_sent_sid_with_cs1=cdcb1d60-06a7-4723-baa3-fccf7e767d80; a2873925c34ecbd2_gr_session_id_cdcb1d60-06a7-4723-baa3-fccf7e767d80=true; Hm_lvt_f0faad39bcf8471e3ab3ef70125152c3=1681741345,1681919995,1681920575,1682002650; messages="[[\"__json_message\"\0540\05425\054\"\\u60a8\\u5df2\\u7ecf\\u767b\\u51fa\"]]:1ppVj3:EC0XUgBJBNrSgqBeup0U7X4HUkGZsxpBfZ8qwVkgkbI"; _ga_PDVPZYN3CW=GS1.1.1682002654.4.0.1682002655.0.0.0; _ga=GA1.2.1481801539.1681399231; Hm_lpvt_f0faad39bcf8471e3ab3ef70125152c3=1682002834; a2873925c34ecbd2_gr_cs1=funny-albattaniics; _gat=1"#);
    let js = request
        .send()
        .await?
        .json::<serde_json::Value>().await?;
    println!("{:#?}", js);
    Ok(())
}