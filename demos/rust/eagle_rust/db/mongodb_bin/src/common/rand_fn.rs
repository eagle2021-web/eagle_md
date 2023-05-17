#![allow(unused)]
use rand::prelude::*;

pub fn rand_word() -> String {
    let mut rng = rand::thread_rng();
    let n = rng.gen::<u8>() % 20 + 1;
    let mut v = vec![0_u8; n as usize];
    for v in v.iter_mut() {
        let cur = rng.gen::<u8>() % 26;
        *v = 'a' as u8 + cur;
    }
    String::from_utf8(v).unwrap()
}

pub fn rand_age() -> u8 {
    let mut rng = rand::thread_rng();
    rng.gen::<u8>() % 128 + 1
}

pub fn rand_id_card() -> String {
    let mut rng = rand::thread_rng();
    let mut v = vec![0_u8; 18 as usize];
    for v in v.iter_mut() {
        let cur = rng.gen::<u8>() % 10;
        *v = '0' as u8 + cur;
    }
    String::from_utf8(v).unwrap()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_rand_word() {
        for _i in 0..1000_000 {
            let s = rand_word();
            assert!(s.len() > 0);
            assert!(s.len() <= 20);
            s.as_bytes().into_iter()
                .for_each(|v| assert!(*v >= 'a' as u8 && *v <= 'z' as u8));
        }
    }

    #[test]
    fn test_rand_age() {
        for _ in 0..1000_000 {
            let age = rand_age();
            assert!(age <= 128);
            assert!(age >= 1);
        }
    }

    #[test]
    fn test_rand_id_card() {
        for _ in 0..100_000 {
            let s = rand_id_card();
            assert_eq!(s.len(), 18);
            for x in s.as_bytes() {
                assert!(*x >= '0' as u8 && *x <= '9' as u8)
            }
        }
    }
}