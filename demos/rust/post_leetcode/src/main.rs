use std::env;
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
    edges: Vec<BigNode>,
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
    let mut body = json!({"query":"\n    query profileSolutionArticles($userSlug: String!, $skip: Int, $first: Int) {\n  solutionArticles(userSlug: $userSlug, skip: $skip, first: $first) {\n    pageInfo {\n      hasNextPage\n    }\n    edges {\n      node {\n        title\n        slug\n        createdAt\n        status\n        question {\n          titleSlug\n          translatedTitle\n          questionFrontendId\n        }\n        upvoteCount\n        topic {\n          viewCount\n        }\n      }\n    }\n  }\n}\n    ","variables":{"userSlug":"ac_oier","skip":0,"first":10},"operationName":"profileSolutionArticles"}
);
    let mut page = 0;
    let args: Vec<String> = env::args().collect();
    if args.len() > 1 {
        page = args[1].parse::<i32>().unwrap();
    }
    body["variables"]["skip"] = json!(10 * page);

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
        .header("Cookie", r#"gr_user_id=1a2397cf-cf1d-4173-9fe7-09e566ea6085; _bl_uid=ahlz8gy5fRh9t5okmv3vrLpde1OX; csrftoken=0h1ZPHod3GV0FSdPHuubnrGt6UB1hOUCNTy8MTnX3lDivQvlqpHq1MBpb3puSQOH; a2873925c34ecbd2_gr_last_sent_cs1=funny-albattaniics; _gid=GA1.2.918915031.1681919995; a2873925c34ecbd2_gr_session_id=cdcb1d60-06a7-4723-baa3-fccf7e767d80; a2873925c34ecbd2_gr_last_sent_sid_with_cs1=cdcb1d60-06a7-4723-baa3-fccf7e767d80; a2873925c34ecbd2_gr_session_id_cdcb1d60-06a7-4723-baa3-fccf7e767d80=true; Hm_lvt_f0faad39bcf8471e3ab3ef70125152c3=1681741345,1681919995,1681920575,1682002650; messages="[[\"__json_message\"\0540\05425\054\"\\u60a8\\u5df2\\u7ecf\\u767b\\u51fa\"]]:1ppVj3:EC0XUgBJBNrSgqBeup0U7X4HUkGZsxpBfZ8qwVkgkbI"; _ga_PDVPZYN3CW=GS1.1.1682002654.4.0.1682002655.0.0.0; _ga=GA1.2.1481801539.1681399231; Hm_lpvt_f0faad39bcf8471e3ab3ef70125152c3=1682002834; a2873925c34ecbd2_gr_cs1=funny-albattaniics; _gat=1"#);
    let js = request
        .send()
        .await?
        .json::<MyStruct>().await?;
    let edges = &js.data.unwrap().solution_articles.edges;
    for edge in edges {
        let node = &edge.node;
        let s2 = format!("https://leetcode.cn/problems/{}/solution/{}/",
                         node.question.title_slug,
                         node.slug);
        println!("{}", s2);
    }
    println!("{:#?}", body["variables"]);
    Ok(())
}