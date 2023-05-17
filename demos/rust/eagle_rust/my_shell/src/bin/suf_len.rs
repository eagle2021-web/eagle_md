#![allow(dead_code, non_camel_case_types)]

const USAGE: &str = "
Usage: suf_len [eagle] [options]
       suf_len --help

clone tag

Commands:
    eagle            Run the benchmark in different modes and print the timings.

Options:
    --dir, -d       file or dir [default file]
    --suffix, -s N      specify suffix &str [default: .json]
    --show      show 5 results [default false]
    --begin, -b N    the results The starting index [default: 0]
    --depth N       the max_depth of the search [default: 20]
    --root, -r N    The starting dir of the search [default: ./]
";

use std::{env};
use std::path::{PathBuf};
use docopt::Docopt;
use walkdir::WalkDir;
#[derive(serde::Deserialize, Debug)]
pub struct Args {
    cmd_eagle: bool,
    flag_show: bool,
    flag_dir: bool,
    flag_begin: usize,
    flag_depth: usize,
    flag_root: String,
    flag_suffix: String,
}


fn main() {
    let args = env::args().collect::<Vec<String>>();
    let args: Args = Docopt::new(USAGE)
        .and_then(|d| d.argv(args).deserialize())
        .unwrap_or_else(|e| e.exit());
    let res = search(&args);
    display(&args, res);
}

fn display(args: &Args, res: Vec<PathBuf>) {
    let n = res.len();
    let res = res.into_iter().map(|v| {
        v.to_str().unwrap().replace("\\", "/").replace("//?/", "").to_string()
    }).collect::<Vec<String>>();
    let arr = &res[args.flag_begin..n.min(args.flag_begin + 5)];
    if args.flag_show {
        println!("{:?}", arr);
    }
    println!("len = {}", n);
}

fn search(args: &Args) -> Vec<PathBuf> {
    let mut res = vec![];
    println!("args = {:?}", args);
    for entry in WalkDir::new(&args.flag_root).max_depth(args.flag_depth) {
        let entry = entry.unwrap();
        let p = entry.path().to_path_buf().canonicalize().unwrap();
        let p_str = p.to_str().unwrap();
        if !p_str.ends_with(&args.flag_suffix) {
            continue;
        }
        if p.is_file() && !args.flag_dir {
            res.push(p);
        } else if p.is_dir() && args.flag_dir {
            res.push(p);
        }
    }
    res
}


#[cfg(test)]
mod tests{
    use std::path::PathBuf;
    use std::str::FromStr;

    #[test]
    fn test_ends_with() {
        let p = PathBuf::from_str("Cargo.toml").unwrap();
        let b = p.ends_with(".toml");
        assert!(!b);
        let p_str = p.to_str().unwrap();
        let b = p_str.ends_with(".toml");
        assert!(b);
    }

}