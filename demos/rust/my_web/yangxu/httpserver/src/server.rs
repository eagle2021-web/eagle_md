use super::router::Router;
use http::httprequest::HttpRequest;
use std::io::prelude::*;
use std::net::TcpListener;
use std::process::id;
use std::str;

pub struct Server<'a> {
    socket_addr: &'a str,
}
impl<'a> Server<'a> {
    pub fn new(socket_addr: &'a str) -> Self {
        Server { socket_addr }
    }
    pub fn run(&self) {
        const BUF_LEN: usize = 100_usize;

        let connection_listener = TcpListener::bind(self.socket_addr).unwrap();
        println!("Running on {}", self.socket_addr);
        for stream in connection_listener.incoming() {
            let mut buf: Vec<u8> = vec![];
            let mut stream = stream.unwrap();
            println!("Connection established");
            loop {
                let mut read_buffer = [0; BUF_LEN];
                let stream_len = stream.read(&mut read_buffer).unwrap();
                println!("stream_len = {}", stream_len);
                buf.extend_from_slice(&read_buffer[0..stream_len]);
                if stream_len != BUF_LEN {
                    break;
                }
            }
            let req: HttpRequest = String::from_utf8(buf).unwrap().into();
            println!("{:#?}", req);
            let js = req.body_to_json();
            if let Ok(val) = &js {
                println!("{:#?}", val);
            }
            Router::route(req, &mut stream)
        }
    }
}
