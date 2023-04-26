use actix_web::web;
use crate::handlers::health::health_check_handler;

pub fn general_routes(cfg: &mut web::ServiceConfig) {
    // cfg.route("/health", web::get().to(health_check_handler));
    cfg.service(web::scope("/love")
        .route("/guantingxin", web::get().to(health_check_handler))
    );
    cfg.service(web::scope("/")
        .route("", web::get().to(health_check_handler))
    );
}