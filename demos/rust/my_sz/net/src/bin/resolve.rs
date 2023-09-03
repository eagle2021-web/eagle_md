use std::net::{SocketAddr, UdpSocket};
use std::time::Duration;
use clap::{App, Arg};
use rand;
use trust_dns::op::{Message, MessageType, OpCode, Query};
use trust_dns::rr::domain::Name;
use trust_dns::rr::record_type::RecordType;
use trust_dns::serialize::binary::*;
fn main(){
    let app = App::new("resolve")
        .about("A simple to use DNS resolver")
        .arg(Arg::with_name("dns-server").short("s").default_value("1.1.1.1"))
        .arg(Arg::with_name("domain-name").default_value("www.baidu.com"))
        .get_matches();
    let domain_name_raw = app    //-  把命令行参数转换为一个有类型的域名。
        .value_of("domain-name").unwrap();
    let domain_name =
        Name::from_ascii(&domain_name_raw).unwrap();
    let dns_server_raw = app    //-
        .value_of("dns-server").unwrap();
    let dns_server: SocketAddr =    //-  把命令行参数转换为一个有类型的DNS服务器。
        format!("{}:53", dns_server_raw)
            .parse()
            .expect("invalid address");
    let mut request_as_bytes: Vec<u8> =    //-  在此清单的后面，解释了为什么要使用两种初始化形式。
        Vec::with_capacity(512);
    let mut response_as_bytes: Vec<u8> =
        vec![0; 512];
    let mut msg = Message::new();    //-  Message表示一个DNS报文，它是一个容器，可以用于保存查询，也可以保存其他信息，例如应答。
    msg.set_id(rand::random::<u16>())
        .set_message_type(MessageType::Query)    //-  在这里指定了这是一个DNS查询，而不是DNS应答。在通过网络传输时，这两者具有相同的表示形式，但在Rust的类型系统中则是不同的。
        .add_query(Query::query(domain_name, RecordType::A))
        .set_op_code(OpCode::Query)
        .set_recursion_desired(true);
    let mut encoder =
        BinEncoder::new(&mut request_as_bytes);    //-  使用BinEncoder把这个Message类型转换为原始字节。
    msg.emit(&mut encoder).unwrap();
    let localhost = UdpSocket::bind("0.0.0.0:0")    //-  0.0.0.0:0表示在一个随机的端口号上监听所有的地址，实际的端口号将由操作系统来分配。
        .expect("cannot bind to local socket");
    let timeout = Duration::from_secs(3);
    localhost.set_read_timeout(Some(timeout)).unwrap();
    localhost.set_nonblocking(false).unwrap();
    let _amt = localhost
        .send_to(&request_as_bytes, dns_server)
        .expect("socket misconfigured");
    let (_amt, _remote) = localhost
        .recv_from(&mut response_as_bytes)
        .expect("timeout reached");
    let dns_message = Message::from_vec(&response_as_bytes)
        .expect("unable to parse response");
    for answer in dns_message.answers(){
        if answer.record_type() == RecordType::A{
            let resource = answer.rdata();
            let ip = resource
                .to_ip_addr()
                .expect("invalid IP address received");
            println!("{}", ip.to_string());
        }
    }
}