use std::error::Error;
use std::net::{SocketAddr, UdpSocket};
use std::time::Duration;
use trust_dns::op::{Message, MessageType, OpCode, Query};
use trust_dns::proto::error::ProtoError;
use trust_dns::rr::domain::Name;
use trust_dns::rr::record_type::RecordType;
use trust_dns::serialize::binary::*;
fn message_id() -> u16{
    let candidate = rand::random();
    if candidate == 0{
        return message_id();
    }
    candidate
}
#[derive(Debug)]
pub enum DnsError{
    ParseDomainName(ProtoError),
    ParseDnsServerAddress(std::net::AddrParseError),
    Encoding(ProtoError),
    Decoding(ProtoError),
    Network(std::io::Error),
    Sending(std::io::Error),
    Receving(std::io::Error),
}
impl std::fmt::Display for DnsError{
    fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result{
        write!(f, "{:#?}", self)
    }
}
impl std::error::Error for DnsError{}    //-  回归默认的方法。
pub fn resolve(
    dns_server_address: &str,
    domain_name: &str,
) -> Result<Option<std::net::IpAddr>, Box<dyn Error>>{
    let domain_name =
        Name::from_ascii(domain_name)
            .map_err(DnsError::ParseDomainName)?;
    let dns_server_address =
        format!("{}:53", dns_server_address);    //-  试图使用原始文本输入来构建内部的数据结构。
    let dns_server: SocketAddr = dns_server_address
        .parse()
        .map_err(DnsError::ParseDnsServerAddress)?;
    let mut request_buffer: Vec<u8> =    //-  因为DNS请求是非常小的，所以我们只需要一小块空间来存放它。
        Vec::with_capacity(64);
    let mut response_buffer: Vec<u8> =    //-  DNS使用UDP传输，它的最大数据包大小是512字节。
        vec![0; 512];
    let mut request = Message::new();    //-  DNS报文可以存放多个查询，但在这里，我们只使用了一个单独的查询。
    request.add_query(
        Query::query(domain_name, RecordType::A)
    );
    request
        .set_id(message_id())
        .set_message_type(MessageType::Query)
        .set_op_code(OpCode::Query)
        .set_recursion_desired(true);    //-  询问我们要连接的DNS服务器，如果它不知道答案，则替我们发出请求。
    let localhost =
        UdpSocket::bind("0.0.0.0:0").map_err(DnsError::Network)?;
    let timeout = Duration::from_secs(5);
    localhost
        .set_read_timeout(Some(timeout))
        .map_err(DnsError::Network)?;    //-  绑定到端口0表示，请求操作系统替我们来分配端口。
    localhost
        .set_nonblocking(false)
        .map_err(DnsError::Network)?;
    let mut encoder = BinEncoder::new(&mut request_buffer);
    request.emit(&mut encoder).map_err(DnsError::Encoding)?;
    let _n_bytes_sent = localhost
        .send_to(&request_buffer, dns_server)
        .map_err(DnsError::Sending)?;
    loop{    //-  在端口上，有很小的可能性会接收到来自某个未知的发送方的另一个UDP消息。为了避免这种情况的发生，我们会忽略这些从不是我们所期望的IP地址处发来的数据包。
        let (_b_bytes_recv, remote_port) = localhost
            .recv_from(&mut response_buffer)
            .map_err(DnsError::Receving)?;
        if remote_port == dns_server{
            break;
        }
    }
    let response =
        Message::from_vec(&response_buffer)
            .map_err(DnsError::Decoding)?;
    for answer in response.answers(){
        if answer.record_type() == RecordType::A{
            let resource = answer.rdata();
            let server_ip =
                resource.to_ip_addr().expect("invalid IP address received");
            return Ok(Some(server_ip));
        }
    }
    Ok(None)
}