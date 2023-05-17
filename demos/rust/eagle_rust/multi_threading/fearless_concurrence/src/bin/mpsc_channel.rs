
fn main() {

}

#[cfg(test)]
mod tests {
    use std::sync::{Arc, mpsc, Mutex};
    use std::thread;
    use std::time::Duration;

    #[test]
    fn test_mpsc_channel() {
        let (tx, rx) = mpsc::channel();

        let tx1 = tx.clone();
        thread::spawn(move || {
            let vals = vec![
                String::from("hi"),
                String::from("from"),
                String::from("the"),
                String::from("thread"),
            ];

            for val in vals {
                tx1.send(val).unwrap();
                thread::sleep(Duration::from_secs(1));
            }
        });

        thread::spawn(move || {
            let vals = vec![
                String::from("more"),
                String::from("messages"),
                String::from("for"),
                String::from("you"),
            ];


            for val in vals {
                tx.send(val).unwrap();
                thread::sleep(Duration::from_secs(1));
            }
        });

        for received in rx {
            println!("Got: {}", received);
        }

    }

    #[test]
    fn test_a() {
        let handle = thread::spawn(|| {
            for i in 1..10 {
                println!("hi number {} from the spawned thread!", i);
                thread::sleep(Duration::from_millis(1));
            }
        });

        for i in 1..5 {
            println!("hi number {} from the main thread!", i);
            thread::sleep(Duration::from_millis(1));
        }

        handle.join().unwrap();

        let v = vec![1, 2, 3];

        let handle = thread::spawn(move || {
            println!("Here's a vector: {:?}", v);
        });

        handle.join().unwrap();
    }

    #[test]
    fn test_mutex() {
        let counter = Arc::new(Mutex::new(0));
        let mut handles = vec![];

        for _ in 0..10 {
            let counter = Arc::clone(&counter);
            let handle = thread::spawn(move || {
                let mut num = counter.lock().unwrap();

                *num += 1;
            });
            handles.push(handle);
        }

        for handle in handles {
            handle.join().unwrap();
        }

        println!("Result: {}", *counter.lock().unwrap());
    }

    #[test]
    fn test_two_channel() {
        let (tx, rx) = mpsc::channel();
        let _handle = thread::spawn(move || {
            for i in 0..10 {
                tx.send(i).unwrap();
                // println!("i = {}", i);
                thread::sleep(Duration::from_millis(51));
            }
        });
        let (tx2, rx2) = mpsc::channel();

        let _handle2 = thread::spawn(move || {
            for i in rx {
                println!("{}", i);
                tx2.send(i * 2).unwrap();
            }
        });
        let handle3 = thread::spawn(move || {
            for i in rx2 {
                println!("i * 2 = {}", i);
            }
        });
        handle3.join().unwrap();
        // handle.join().unwrap();
        let ok = "Ok";
        println!("{}", ok);
    }
}