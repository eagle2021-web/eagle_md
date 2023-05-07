#![allow(dead_code, unused_imports, non_camel_case_types)]

const USAGE: &str = "
Usage: git_clone [eagle] [options]
       git_clone --help

clone tag

Commands:
    eagle            Run the benchmark in different modes and print the timings.

Options:
    --thread-size N     Number of 32-bit words to sort [default: 4] (1GB)
    --help, -h          Show this message.
    --source, -s N      source json path
    --dir, -d N         root dir which all repositories cloned in [default: d:/eagle_repos/]
    --result, -r N      json path which result written into [default: ./tmp22.json]
    --clone-new, -c     remove old project and clone new one
";

use std::{env, fs, thread};
use std::ops::Index;
use std::path::{Path, PathBuf};
use std::process::Command;
use std::str::FromStr;
use serde_json::{json, Value};
use std::sync::{Arc, mpsc, Mutex};
use std::sync::mpsc::Sender;
use std::time::Duration;
use common_utils::replace_str;

#[derive(serde::Deserialize, Debug)]
pub struct Args {
    cmd_eagle: bool,
    flag_source: String,
    flag_result: String,
    flag_dir: String,
    flag_thread_size: usize,
    flag_clone_new: bool
}

use docopt::Docopt;
use std::time::Instant;

fn main() {
    let args = env::args().collect::<Vec<String>>();
    let args: Args = Docopt::new(USAGE)
        .and_then(|d| d.argv(args).deserialize())
        .unwrap_or_else(|e| e.exit());
    println!("args = {:?}", args);
    clone_all(args);
}

fn clone_all(args: Args) {
    let s = fs::read_to_string(&args.flag_source).expect("Failed to read");
    let arr_value: Value = serde_json::from_str(&s).expect("not value");
    let (ts, tr) = mpsc::channel();
    let counter = Arc::new(Mutex::new(0));
    let mut handles = vec![];
    let arr = Arc::new(arr_value.as_array().unwrap().clone());
    let args = Arc::new(args);
    for _ in 0..args.flag_thread_size {
        let counter = Arc::clone(&counter);
        let ts = mpsc::Sender::clone(&ts);
        let arr = Arc::clone(&arr);
        let args= Arc::clone(&args);
        let handle = thread::spawn(move || {
            loop {
                let index;
                {
                    let mut num = counter.lock().unwrap();

                    index = *num;
                    if index >= arr.len() as i32 {
                        break;
                    }
                    *num += 1;
                }
                let value = &(*arr);
                let clone_obj = &value[index as usize];
                clone_one(&ts, clone_obj, &args);
            }
        });
        handles.push(handle);
    }
    drop(ts);
    let mut res = json!([]);
    let mut ok = 0;
    for v in tr {
        res.as_array_mut().unwrap().push(v);
        ok += 1;
        println!("ok += 1, cur = {}", ok);
    }

    for handle in handles {
        handle.join().unwrap();
    }

    println!("&args.flag_result = {:?}", &args.flag_result);
    
    fs::write(&args.flag_result, res.to_string()).expect("Failed to write");
}

fn clone_one(sender: &Sender<Value>, value: &Value, args: &Arc<Args>) {
    let obj = value.as_object().expect("not obj");
    let url = obj.get("url").expect("no url").as_str().expect("not str");
    let tag = obj.get("tag").expect("no tag").as_str().expect("not str");
    let path = url.replace("https://", &args.flag_dir);
    let path_buf = PathBuf::from_str(&path)
        .expect("not path")
        .join(&tag);
    let mut js = value.clone();

    js["clone_msg"] = json!("ok");
    js["project_path"] = json!(replace_str(path_buf.to_str().unwrap()));
    js["clone_ok"] = json!(0);

    if path_buf.exists() {
        if args.flag_clone_new {
            fs::remove_dir_all(&path_buf).unwrap();
        } else {
            sender.send(js).expect("err occurred about sent value");
            return;
        }
    }
    let mut c = Command::new("git");
    c.args(["clone", "--depth", "1", "--branch", tag, url, path_buf.to_str().unwrap()]);
    // --depth 1 --branch <tag_name> <repo_url>
    let output = c.output().expect("no output");
    let code = output.status.code().unwrap_or(-1);

    let path_str = replace_str(path_buf.to_str().unwrap());
    println!("project_path = {}", path_str);
    println!("output.status = {:?}", output.status);

    js["clone_ok"] = json!(code);

    if code != 0 {
        unsafe {
            let msg = String::from_utf8_unchecked(output.stderr.clone());
            js["clone_msg"] = json!(&msg);
        }
    }
    sender.send(js).expect("err occurred about sent value");
}

pub fn git_cur_tag(path: &str) -> Option<String> {
    let mut c = Command::new("git");
    c.args(["-C", path, "branch"]);
    let output = c.output();
    if let Ok(o) = output {
        println!("ok");
        let msg = String::from_utf8(o.stdout).unwrap();
        return Some(msg);
    }
    println!("not ok");
    return None;
}

#[cfg(test)]
mod tests {
    use crate::git_cur_tag;

    #[test]
    #[ignore]
    fn test_git_cur_tag() {
        let p = "d:/tmp/gitee.com/egzosn/pay-java-parent";
        let a = git_cur_tag(p);
        if let Some(a) = a {
            println!("{}", a);
        }
    }


}