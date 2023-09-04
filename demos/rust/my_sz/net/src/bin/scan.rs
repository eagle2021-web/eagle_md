use rustscan::{Scan, ScanRange};

#[tokio::main]
async fn main() {
    let scan = Scan::new()
        .range(ScanRange::new("127.0.0.1/24").unwrap())
        .threads(100);

    let result = scan.run().await.unwrap();

    for host in result.all_hosts() {
        println!("Host: {}", host.addr());
        for port in host.ports() {
            println!("Port {} is open", port);
        }
    }
}