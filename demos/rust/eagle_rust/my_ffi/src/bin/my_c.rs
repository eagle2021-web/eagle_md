use std::ffi::CString;
use std::os::unix::io::FromRawFd;
use std::os::unix::io::RawFd;
use std::fs::File;
use std::io::prelude::*;

fn main() {
    // 创建一个C字符串，表示文件名
    let filename = CString::new("test.txt").expect("CString creation failed");

    // 打开文件
    let file_ptr = unsafe { libc::fopen(filename.as_ptr(), "w\0".as_ptr() as *const _) };
    if file_ptr.is_null() {
        panic!("Failed to open file");
    }

    let file = unsafe { File::from_raw_fd(file_ptr as RawFd) };

    // 写入文件
    let content = "Hello, Rust!";
    if let Err(err) = file.write_all(content.as_bytes()) {
        panic!("Failed to write to file: {}", err);
    }

    // 关闭文件
    file.sync_all().expect("Failed to sync file");
    unsafe { libc::fclose(file_ptr) };
}