use mongodb::bson::Document;
use mongodb::Client;
use mongodb::options::{ClientOptions};
use mongodb::bson::doc;

#[tokio::main]
async fn main() -> Result<(), mongodb::error::Error> {
    let mut client_options = ClientOptions::parse("mongodb://localhost:27017").await?;
    client_options.app_name = Some("My App".to_string());
    let client = Client::with_options(client_options)?;
    let db = client.database("mydb");
    let food = db.collection::<Document>("food");
    let arr = vec![
        doc! {"fruit": ["apple", "banana", "peach"]},
        doc! {"fruit": ["apple", "kumquat", "orange"]},
        doc! {"fruit": ["cherry", "banana", "apple"]},
    ];
    food.insert_many(arr, None).await?;
    Ok(())
}