#[macro_use]
extern crate diesel;
extern crate dotenv;

use diesel::prelude::*;
use diesel::pg::PgConnection;
use dotenv::dotenv;
use std::env;
pub mod schema;
pub mod table_models;
pub fn establish_connection() -> PgConnection {
    dotenv().ok();

    let database_url = env::var("REMOTE_POST_URL")
        .expect("DATABASE_URL must be set");
    println!("{}", database_url);
    PgConnection::establish(&database_url)
        .expect(&format!("Error connecting to {}", database_url))
}
