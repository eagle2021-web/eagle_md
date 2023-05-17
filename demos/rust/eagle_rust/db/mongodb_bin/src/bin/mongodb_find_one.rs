use mongodb::bson::Document;
use mongodb::Client;
use mongodb::options::{ClientOptions, FindOneOptions};
use mongodb::bson::doc;

#[tokio::main]
async fn main() -> Result<(), mongodb::error::Error> {
    let mut client_options = ClientOptions::parse("mongodb://localhost:27017").await?;
    client_options.app_name = Some("My App".to_string());
    let client = Client::with_options(client_options)?;
    let db = client.database("mydb");
    let food = db.collection::<Document>("food");
    let find_options = FindOneOptions::builder()
        .return_key(false)
        .projection(doc! {"_id": 0})
        .build();
    let res = food.find_one(doc! {"fruit": "banana"}, find_options)
        .await?;
    if let Some(d) = &res {
        println!("{}", d);
    }
    Ok(())
}