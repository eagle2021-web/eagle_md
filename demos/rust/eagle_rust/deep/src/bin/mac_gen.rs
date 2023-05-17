#![allow(unused)]
extern crate rand;

use rand::RngCore;
use std::fmt;
use std::fmt::{Display, Formatter};

#[derive(Debug)]
struct MacAddress([u8; 6]);

impl Display for MacAddress {
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        let octet = &self.0;
        write!(
            f,
            "{:02x}:{:02x}:{:02x}:{:02x}:{:02x}:{:02x}",
            octet[0], octet[1], octet[2], octet[3], octet[4], octet[5])
    }
}

impl MacAddress {
    fn new() -> MacAddress {
        let mut octets: [u8; 6] = [0; 6];
        rand::thread_rng().fill_bytes(&mut octets);
        octets[0] |= 0b_0000_0011;
        MacAddress { 0: octets }
    }

    fn is_local(&self) -> bool {
        (self.0[0] & 2_u8) == 2_u8
    }

    fn is_unicast(&self) -> bool {
        (self.0[0] & 1_u8) == 1_u8
    }
}

fn main() {}

#[cfg(test)]
mod test {
    use crate::MacAddress;

    #[test]
    fn test_new() {
        let mac = MacAddress::new();
        println!("mac = {}", mac);
        assert!(mac.is_local());
        assert!(mac.is_unicast());
    }

    #[test]
    fn test_or() {
        let a = 0b_0000_0011;
        for i in 0..4 {
            let b = i as u8 | a;
            assert_eq!(a, b);
        }
    }
}