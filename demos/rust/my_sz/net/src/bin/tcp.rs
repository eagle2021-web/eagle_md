use std::io::prelude::*;
use std::net::TcpStream;
fn main() -> std::io::Result<()>{
    let host = "www.baidu.com:80";    //-  必须显式指定端口号（80），TcpStream并不知道这将成为一个HTTP的请求。
    let mut conn =
        TcpStream::connect(host)?;
    conn.write_all(b"GET / HTTP/1.0")?;
    conn.write_all(b"\r\n")?;    //-  在许多的网络协议中，都是用\r\n来表示换行符的。
    conn.write_all(b"Host: www.baidu.com")?;
    conn.write_all(b"\r\n\r\n")?;    //-  两个换行符表示本次请求结束。
    std::io::copy(    //-  std::io::copy() 会把字节流从一个Reader写到一个Writer中。
                      &mut conn,
                      &mut std::io::stdout()
    )?;
    Ok(())
}