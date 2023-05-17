#![allow(unused)]
extern crate crossbeam;
extern crate crossbeam_channel;
use std::{thread};
use std::fs::File;
use std::io::{BufReader, Read};
use std::path::Path;
use std::time::Duration;
use crossbeam_channel::bounded;
use lazy_static::lazy_static;
use std::sync::Mutex;
use ring::digest::{Digest, SHA256};
lazy_static! {
        static ref FRUIT: Mutex<Vec<String>> = Mutex::new(Vec::new());
    }
fn main() {
    let (snd1, rcv1) = bounded(1);
    let (snd2, rcv2) = bounded(1);
    let n_msgs = 4;
    let n_workers = 2;

    crossbeam::scope(|s| {
        // 生产者线程
        s.spawn(|_| {
            for i in 0..n_msgs {
                snd1.send(i).unwrap();
                println!("Source sent {}", i);
            }
            // 关闭信道 —— 这是退出的必要条件
            // for 巡海在工作线程中
            drop(snd1);
        });

        // 由 2 个线程并行处理
        for _ in 0..n_workers {
            // 从数据源发送数据到接收器，接收器接收数据
            let (sendr, recvr) = (snd2.clone(), rcv1.clone());
            // 在不同的线程中衍生工人
            s.spawn(move |_| {
                thread::sleep(Duration::from_millis(500));
                // 接收数据，直到信道关闭前
                for msg in recvr.iter() {
                    println!("Worker {:?} received {}.",
                             thread::current().id(), msg);
                    sendr.send(msg * 2).unwrap();
                }
            });
        }
        // 关闭信道，否则接收器不会关闭
        // 退出 for 循坏
        drop(snd2);

        // 接收器
        for msg in rcv2.iter() {
            println!("Sink received {}", msg);
        }
    }).unwrap();
}

fn find_max(arr: &[i32]) -> Option<i32> {
    const THRESHOLD: usize = 2;

    if arr.len() <= THRESHOLD {
        return arr.iter().cloned().max();
    }

    let mid = arr.len() / 2;
    let (left, right) = arr.split_at(mid);

    crossbeam::scope(|s| {
        let thread_l = s.spawn(|_| find_max(left));
        let thread_r = s.spawn(|_| find_max(right));

        let max_l = thread_l.join().unwrap()?;
        let max_r = thread_r.join().unwrap()?;

        Some(max_l.max(max_r))
    }).unwrap()
}

fn insert(fruit: &str) -> Result<(), &str> {
    let mut db =
        FRUIT.lock().map_err(|_| "Failed to acquire MutexGuard")?;
    db.push(fruit.to_string());
    Ok(())
}


fn compute_digest<P: AsRef<Path>>(filepath: P) -> Result<(Digest, P), std::io::Error> {
    let mut buf_reader = BufReader::new(File::open(&filepath)?);
    let mut context = ring::digest::Context::new(&SHA256);
    let mut buffer = [0; 1024];

    loop {
        let count = buf_reader.read(&mut buffer)?;
        if count == 0 {
            break;
        }
        context.update(&buffer[..count]);
    }

    Ok((context.finish(), filepath))
}

#[cfg(test)]
mod tests {
    use crossbeam_channel::unbounded;
    use crate::{compute_digest, find_max, FRUIT, insert};
    use error_chain::error_chain;


    error_chain! { }


    #[test]
    fn test_find_max() {
        let arr = &[1, 25, -4, 10];
        let max = find_max(arr);
        assert_eq!(max, Some(25));
    }

    #[test]
    fn test_aa() {
        let (snd, rcv) = unbounded();
        let n_msgs = 50000;

        crossbeam::scope(|s| {
            s.spawn(|_| {
                for i in 0..n_msgs {
                    snd.send(i).unwrap();
                    // thread::sleep(time::Duration::from_millis(10));
                }
            });
        }).unwrap();
        println!("==========");
        let b:i32 = (0..50000).sum();
        let a: i32 = rcv.try_iter().collect::<Vec<i32>>().iter().sum();
        assert_eq!(a, b);
        snd.send(1).unwrap();
        let a = rcv.recv().unwrap();
        println!("{a}");
    }

    #[test]
    fn test_mutex() -> Result<()> {
        insert("apple")?;
        insert("orange")?;
        insert("peach")?;
        {
            let db = FRUIT.lock().map_err(|_| "Failed to acquire MutexGuard")?;

            db.iter().enumerate().for_each(|(i, item)| println!("{}: {}", i, item));
        }
        insert("grape")?;
        Ok(())
    }

    #[test]
    fn test_compute_digest() -> Result<()> {
        let a = compute_digest("E:/斗破苍穹.txt").unwrap().0;
        // SHA256:d55ab8a13d3f6e4f341efcbdca48b3545cd54bed9f644318e4f4f4f8ae42dedb
        println!("{:?}", a);
        Ok(())
    }
}