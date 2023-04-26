extern crate diesel_demo;
extern crate diesel;


use self::diesel_demo::*;
use self::models::*;
use self::diesel::prelude::*;
use diesel::{insert_into, Insertable, Queryable};
use diesel_demo::table_models::Post;

fn main() {
    use diesel_demo::schema::posts::dsl::*;

    let connection = establish_connection();
    let results = posts
        .limit(5)
        .load::<Post>(&connection)
        .expect("Error loading posts");

    println!("Displaying {} posts", results.len());
    for post in results {
        println!("{}", post.title);
        println!("----------\n");
        println!("{}", post.body);
    }
    let a = insert_into(posts)
        .values((title.eq("hello today"), body.eq("body"), published.eq(true)))
        .execute(&connection);
    let b = a.unwrap();
}
