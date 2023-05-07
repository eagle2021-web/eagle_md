use webbrowser;

fn main() {
    let url = "https://doc.rust-lang.org/rust-by-example/macros/designators.html";
    if webbrowser::open(url).is_ok() {
        println!("open {}", "ok");
    }
}
