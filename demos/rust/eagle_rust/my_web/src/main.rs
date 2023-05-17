use std::io;
use std::string::ToString;
use std::sync::Mutex;
use actix_web::{App, HttpResponse, HttpServer, Responder, web};
use serde::{Deserialize, Serialize};
use crate::state::{AppState, AppState2};
use actix_cors::Cors;
#[derive(Debug, Serialize, Deserialize)]
pub struct Student {
    name: String
}

mod state;
static S: &str = r#"
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        body {
            display: grid;
            place-items: center;
        }
        #d1 {
            display: grid;
            place-items: center;
            margin-top: 200px;
        }
        .abc {
            color: blueviolet;
            font-size: 70px;

        }
    </style>
</head>
<body>
<div id="d1"><p class="abc">陈旺英爱关婷心!</p></div>
</body>
</html>
"#;
// 配置route
pub fn general_routes(cfg: &mut web::ServiceConfig) {
    // cfg.route("/health", web::get().to(health_check_handler));
    cfg.service(web::scope("/love")
        .route("/guantingxin", web::get().to(health_check_handler))
        .route("/student", web::post().to(student_handler))
    );
}
pub async fn student_handler(student: web::Json<Student>) -> impl Responder {
    println!("student = {:?}", student);
    
    HttpResponse::Ok().header("content-type", "text/html; charset=UTF-8").json("ok")
}
pub async fn health_check_handler() -> impl Responder {
    println!("11");
    HttpResponse::Ok().header("content-type", "text/html; charset=UTF-8").body(S.clone())
}

#[actix_rt::main]
async fn main() -> io::Result<()> {
    println!("Hello, world!");
    let shared_data = web::Data::new(AppState {
        health_check_response: "I'm Ok.".to_string(),
        visit_count: Mutex::new(0),
    });

    let shared_data2 = web::Data::new(AppState2 {
        health_check_response: "I'm Ok2.".to_string(),
        visit_count: Mutex::new(0),
    });
    let app = move || {
        App::new()
            .wrap(

                Cors::default()
                    .allow_any_origin()
                    .allow_any_method()
                    .allow_any_header()
                    .max_age(3600)
            )
            .app_data(shared_data.clone())
            .app_data(shared_data2.clone())
            .configure(general_routes)
    };
    HttpServer::new(app).bind("0.0.0.0:9999")?.run().await
}
