use postgres::{Client, NoTls};

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // 建立数据库连接
    let mut client = Client::connect("postgres://postgres:1@localhost:5432/postgres", NoTls)?;

    // 执行 SQL 查询
    for row in client.query("SELECT id, name FROM student", &[])? {
        let id: i32 = row.get(0);
        let name: String = row.get(1);
        println!("id: {}, name: {}", id, name);
    }

    Ok(())
}
