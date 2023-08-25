use std::fs;
use std::path::PathBuf;
use clap::{App, Arg};
use rayon::iter::{IntoParallelRefIterator, ParallelIterator};

fn main() {
    let app = App::new("cnt_size")
        .about("count dir size")
        .arg(Arg::with_name("root").short("r")
            .default_value("./"))
        .arg(Arg::with_name("par").short("-p")
            .default_value("true"))
        .get_matches();
    let root_dir = app.value_of("root").expect("no root arg");
    let par = app.value_of("par").expect("no par arg");
    let par: bool = par.parse().expect("not bool");

    let start = chrono::Local::now();
    let dir = walkdir::WalkDir::new(&root_dir);
    let mut arr = vec![];
    for v in dir.into_iter() {
        if let Ok(p) = v {
            if p.path().is_file() {
                arr.push(p.path().to_path_buf());
            }
        }
    }
    let cnt;
    if par {
        cnt = cnt_par(arr);
    } else {
        cnt = cnt_single(arr);
    }
    echo_size(cnt);
    let end = chrono::Local::now();
    println!("{}", end - start);
}


fn cnt_file(sub_path: &PathBuf) -> usize {
    let f = fs::File::open(sub_path);
    let mut cnt = 0;
    match f {
        Ok(f2) => { cnt = f2.metadata().expect("read metadata err").len() as usize }
        Err(_) => {}
    }
    cnt
}
fn cnt_single(arr: Vec<PathBuf>) -> usize {
    arr.iter().map(cnt_file).sum()
}

fn cnt_par(arr: Vec<PathBuf>) -> usize {
    arr.par_iter().map(cnt_file).sum()
}

fn echo_size(cnt: usize) {
    const SIZE: f64 = 1024_f64;
    let mut cnt = cnt as f64;
    let mut index = 0;
    let candidates = vec!["B", "KB", "MB", "GB"];
    while cnt >= SIZE {
        cnt /= SIZE;
        index += 1;
    }
    let suf = candidates[index];
    let s = format!("{}{}", cnt, suf);
    println!("size = {}", s);
}

#[cfg(test)]
mod test {
    use rayon::prelude::*;
    use std::fs;
    use test_cfg::project_root;


    #[test]
    fn test_cpu_num() {
        let num = num_cpus::get();
        println!("cpu_num = {}", num);
        let num = num_cpus::get_physical();
        println!("cpu_physical_num =  {num}");
    }

    #[test]
    fn test_a() {
        let path = project_root();
        let p = path.join("my_shell/src/bin/cnt_size.rs");
        let p2 = path.join("my_shell/src/bin/cp_bin.rs");
        let input = vec![p, p2];
        input
            .par_iter()
            .for_each(|sub_path| {
                let f = fs::File::open(sub_path).expect("open file err");
                let cnt = f.metadata().expect("read metadata err").len() as usize;
                println!("{cnt}");
            })
        ;
    }

    #[test]
    fn test_b() {
        let p = project_root();

        let dir = walkdir::WalkDir::new(p)
            .min_depth(1)
            .max_depth(100);
        let mut arr = vec![];
        for v in dir.into_iter() {
            if let Ok(a) = v {
                arr.push(a.file_name().to_os_string());
            }
        }
        print!("len = {}", arr.len());

    }
}