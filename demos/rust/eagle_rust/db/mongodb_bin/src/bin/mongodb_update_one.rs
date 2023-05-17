use mongodb::bson::Document;
use mongodb::Client;
use mongodb::options::{ClientOptions, UpdateOptions};
use mongodb::bson::doc;

#[tokio::main]
async fn main() -> Result<(), mongodb::error::Error> {
    let mut client_options = ClientOptions::parse("mongodb://localhost:27017").await?;
    client_options.app_name = Some("My App".to_string());
    let client = Client::with_options(client_options)?;
    let db = client.database("mydb");
    let food = db.collection::<Document>("food");
    let options = UpdateOptions::builder()
        .upsert(true)
        .build();
    let d = food.update_one(doc! {
            "fruit": ["apple", "banana", "peach2"]
        }, doc! {
            "$set": {
                "fruit": "watermelon"
            }
        }, options).await?;
    println!("{:?}", d);
    Ok(())
}