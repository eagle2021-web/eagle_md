use std::fs::File;
use std::io::BufReader;
use std::io::prelude::*;
use regex::Regex;
use clap::{App,Arg};
fn main(){
    //run --package my_sz --bin 读文件匹配字符串  -- about e:\hello.txt
    let args = App::new("grep-lite")
        .version("0.1")
        .about("searches for patterns")
        .arg(Arg::with_name("pattern")
            .help("The pattern to search for")
            .takes_value(true)
            .required(true))
        .arg(Arg::with_name("input")
            .help("File to search")
            .takes_value(true)
            .required(true))
        .get_matches();
    let pattern = args.value_of("pattern").unwrap();
    let re = Regex::new(pattern).unwrap();
    let input = args.value_of("input").unwrap();
    let f = File::open(input).unwrap();
    let reader = BufReader::new(f);
    for line_ in reader.lines(){
        let line = line_.unwrap();
        match re.find(&line){    //  line是String类型，但是re.find() 方法需要 &str类型作为参数。
            Some(_) => println!("{}", line),
            None => (),
        }
    }
}