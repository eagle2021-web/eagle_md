use mongodb::bson::Document;
use mongodb::Client;
use mongodb::options::{ClientOptions, UpdateOptions};
use mongodb::bson::doc;
use chrono::prelude::*;
use common::rand_fn::*;

#[path = "../common/mod.rs"]
mod common;

#[tokio::main]
async fn main() -> Result<(), mongodb::error::Error> {
    let local: DateTime<Local> = Local::now(); //
    let mut client_options = ClientOptions::parse("mongodb://localhost:27017").await?;
    client_options.app_name = Some("my_app".to_string());
    let client = Client::with_options(client_options)?;
    let db = client.database("mydb");
    let users = db.collection::<Document>("users");
    let options = UpdateOptions::builder()
        .upsert(true)
        .build();
    for i in 0..100_000 {
        println!("{}", i);
        let name = rand_word();
        let age = rand_age();
        let id_card = rand_id_card();
        let update_result = users.update_many(doc! {
                "id_card": &id_card
            }, doc! {
                "$set": {
                    "id_card": &id_card,
                    "age": age as i32,
                    "name": name
                }
            }, options.clone()).await?;
        println!("{:?}", update_result);
    }
    let local_end: DateTime<Local> = Local::now();
    let interval = (local_end - local).num_seconds();
    println!("{:?}", interval);
    Ok(())
}