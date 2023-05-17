use futures::TryStreamExt;
use mongodb::Client;
use mongodb::options::{ClientOptions, FindOptions};
use mongodb::bson::doc;
use common::model::Book;

#[path = "../common/mod.rs"]
mod common;

#[tokio::main]
async fn main() -> Result<(), mongodb::error::Error> {
    let mut client_options = ClientOptions::parse("mongodb://localhost:27017").await?;
    client_options.app_name = Some("My App".to_string());
    let client = Client::with_options(client_options)?;
    let db = client.database("mydb");
    let typed_collection = db.collection::<Book>("books");
    let filter = doc! { "author": "George Orwell" };
    let find_options = FindOptions::builder()
        .sort(doc! { "title": 1 }).limit(20).build();

    let mut cursor = typed_collection.find(filter, find_options).await?;
    while let Some(book) = cursor.try_next().await? {
        println!("title: {:?}", book);
    }
    Ok(())
}