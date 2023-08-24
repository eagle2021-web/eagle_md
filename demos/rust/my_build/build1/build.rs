use std::env;
use std::fs::File;
use std::io::Write;
use std::path::Path;

fn main() {
    let out_dir = env::var("OUT_DIR").unwrap();
    // 参考eagle_md\demos\rust\my_build\target\debug\build\build1-dca030e1c8dbc99f\out
    println!("{}", out_dir);

    let dest_path = Path::new(&out_dir).join("hello.rs");
    let mut f = File::create(&dest_path).unwrap();

    f.write_all(b"
        fn say_hello() -> &'static str {
            \"hello\"
        }
    ").unwrap();

}
