use actix_web::{HttpResponse, Responder};

pub async fn health_check_handler() -> impl Responder {
    println!("11");
    HttpResponse::Ok()
        .append_header(("content-type", "text/html; charset=UTF-8"))
        .body("health")
}