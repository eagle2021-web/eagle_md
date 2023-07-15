use ssh2::Session;
use std::io::Read;

fn main() {
    let host = "192.168.0.131";
    let port = 22;
    let username = "root";
    let password = "1";

    let tcp = std::net::TcpStream::connect((host, port)).unwrap();
    let mut sess = Session::new().unwrap();
    sess.set_tcp_stream(tcp);
    sess.handshake().unwrap();

    sess.userauth_password(username, password).unwrap();

    let mut channel = sess.channel_session().unwrap();
    channel.exec("free -h; df -h").unwrap();

    let mut output = String::new();
    channel.read_to_string(&mut output).unwrap();

    println!("System Resources:\n{}", output);
}