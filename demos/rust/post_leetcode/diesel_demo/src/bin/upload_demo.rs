use actix_multipart::Multipart;
use actix_web::{web, App, Error, HttpResponse, HttpServer, patch};
use futures::{StreamExt, TryStreamExt};
use multer::{Constraints, SizeLimit};
use std::io::Write;
use std::sync::Mutex;
use actix_cors::Cors;
use uuid::Uuid;
use crate::models::state::{AppState, AppState2};
use crate::routers::file::file_routes;
use crate::routers::general::general_routes;

#[path = "../models/mod.rs"]
mod models;
#[path = "../routers/mod.rs"]
mod routers;
#[path = "../handlers/mod.rs"]
mod handlers;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    println!("Hello, world!");
    let shared_data = web::Data::new(AppState {
        health_check_response: "I'm Ok.".to_string(),
        visit_count: Mutex::new(0),
    });

    let shared_data2 = web::Data::new(AppState2 {
        health_check_response: "I'm Ok2.".to_string(),
        visit_count: Mutex::new(0),
    });
    Command
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
            .configure(file_routes)
    };
    HttpServer::new(app).bind("0.0.0.0:9999")?.run().await
}

