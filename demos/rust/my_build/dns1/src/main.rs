use std::env;
use trust_dns_resolver::Resolver;
use trust_dns_resolver::config::*;
use trust_dns::rr::record_type::RecordType;

fn main() {
    let mut args: Vec<String> = env::args().collect();
    println!("{}", args.len());
    if args.len() != 2 {
        args.push("www.baidu.com".to_string());
    }
    if args.len() != 2 {
        eprintln!("Please provide a name to query!");
        std::process::exit(1);
    }
    let query = format!("{}.", args[1]);

    println!("use default config:");
    // 使用 Resolver::new(ResolverConfig::default(), ResolverOpts::default())
    // 方法创建一个默认配置的 DNS 解析器实例 resolver。
    let resolver =
        Resolver::new(ResolverConfig::default(), ResolverOpts::default()).unwrap();
    // 使用 resolver.lookup_ip(query.as_str()) 方法进行域名解析，返回一个包含 IP 地址的 response。
    let response = resolver.lookup_ip(query.as_str());
    for ans in response.iter() {
        println!("bb {:#?}", ans);
    }

    println!("use system config:");
    let resolver =
        Resolver::from_system_conf().unwrap();
    let response = resolver.lookup_ip(query.as_str());
    for ans in response.iter() {
        println!("{:#?}", ans);
    }

    println!("use ns:");
    let ns = resolver.lookup(query.as_str(), RecordType::NS);
    for ans in ns.iter() {
        println!("{:?}", ans);
    }

    println!("Hello, world!");
}
