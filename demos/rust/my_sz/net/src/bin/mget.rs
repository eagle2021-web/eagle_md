use clap::{App, Arg};
use smoltcp::phy::TapInterface;
use url::Url;
#[path="../dns.rs"]
mod dns;
#[path="../ethernet.rs"]
mod ethernet;
#[path="../http.rs"]
mod http;
fn main(){
    let app = App::new("mget")
        .about("GET a webpage, manually")
        .arg(Arg::with_name("url").required(true))    //-  需要一个URL才能从中下载数据。
        .arg(Arg::with_name("tap-device").required(true))    //-  需要使用TAP网络设备来连接。
        .arg(
            Arg::with_name("dns-server")
                .default_value("1.1.1.1"),    //-  让用户可以选择要使用哪个DNS服务器。
        )
        .get_matches();    //-  解析命令行参数。
    let url_text = app.value_of("url").unwrap();
    let dns_server_text =
        app.value_of("dns-server").unwrap();
    let tap_text = app.value_of("tap-device").unwrap();
    let url = Url::parse(url_text)    //-  验证命令行参数。
        .expect("error: unable to parse <url> as a URL");
    if url.scheme() != "http"{    //-
        eprintln!("error: only HTTP protocol supported");
        return;
    }
    let tap = TapInterface::new(&tap_text)    //-
        .expect(
            "error: unable to use <tap-device> as a \
network interface",
        );
    let domain_name =
        url.host_str()
            .expect("domain name required");    //-
    let _dns_server: std::net::Ipv4Addr =
        dns_server_text
            .parse()
            .expect(    //-
                        "error: unable to parse <dns-server> as an \
IPv4 address",
            );
    let addr =
        dns::resolve(dns_server_text, domain_name)    //-  把URL的域名转换成可以用于连接的IP地址。
            .unwrap()
            .unwrap();
    let mac = ethernet::MacAddress::new().into();    //-  生成一个随机的Unicode编码形式的MAC地址。
    http::get(tap, mac, addr, url).unwrap();    //-  创建HTTP GET请求
}

