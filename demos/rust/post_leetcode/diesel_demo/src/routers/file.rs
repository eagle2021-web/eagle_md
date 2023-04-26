use actix_web::middleware::ErrorHandlerResponse::Response;
use actix_web::web;
use crate::handlers::file::handle_upload;
use crate::handlers::health::health_check_handler;

pub fn file_routes(cfg: &mut web::ServiceConfig) {
    // cfg.route("/health", web::get().to(health_check_handler));
    cfg.service(web::scope("/file")
        .route("/upload", web::post().to(handle_upload))

    );

}