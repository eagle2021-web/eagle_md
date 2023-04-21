use diesel::prelude::*;
use serde::{Deserialize, Serialize};
#[macro_use]
extern crate diesel;
extern crate serde;

diesel::table! {
    users {
        id -> Integer,
        name -> Text,
        hair_color -> Nullable<Text>,
        created_at -> Timestamp,
        updated_at -> Timestamp,
    }
}
#[derive(Queryable, Serialize, Deserialize)]
pub struct User {
    pub id: i32,
    pub name: String,
}
#[derive(Insertable, Serialize, Deserialize)]
#[table_name="users"]
struct NewUser<'a> {
    name: &'a str,
}
fn insert_users() -> QueryResult<usize> {


// Insert one record at diesel_demo time

    let new_user = NewUser { name: "Ruby Rhod" };

    diesel::insert_into(users)
        .values(&new_user)
        .execute(&connection)
        .unwrap();

// Insert many records

    let new_users = vec![
        NewUser { name: "Leeloo Multipass", },
        NewUser { name: "Korben Dallas", },
    ];

    let inserted_names = diesel::insert_into(users)
        .values(&new_users)
        .execute(&connection)
        .unwrap();
}
fn establish_connection() -> PgConnection {
    let database_url = "postgres://postgres:1@localhost:5432/postgres";
    PgConnection::establish(&database_url).expect(&format!("Error connecting to {}", database_url))
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let a = insert_users();

    Ok(())
}
