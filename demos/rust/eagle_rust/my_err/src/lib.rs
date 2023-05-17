#![allow(unused)]
extern crate serde;
extern crate toml;

#[macro_use]
extern crate failure;

use failure::Fail;
use serde_json;

#[derive(Debug, Fail)]
pub enum EagleErr {
    #[fail(display = "IO error {}.", _0)]
    Io(#[cause] std::io::Error),
    #[fail(display = "Parse error {}.", _0)]
    ParseInt(#[cause] std::num::ParseIntError),
    #[fail(display = "ParseSerdeJson error {}.", _0)]
    ParseSerdeJson(#[cause] serde_json::error::Error),
    #[fail(display = "ParseAddr error {}.", _0)]
    ParseAddr(#[cause] std::net::AddrParseError),

}
// trust_dns::proto::error::ProtoError
macro_rules! op3 {
    ($a: ident, $b: ident, $c: ident, $enum: ident) => {
        impl From<$a::$b::$c> for EagleErr {
            fn from(error: $a::$b::$c) -> Self {
                // println!("Error: {:?}", error);
                EagleErr::$enum(error)
            }
        }
    };
}

op3!(serde_json, error, Error, ParseSerdeJson);
op3!(std, io, Error, Io);
op3!(std, num, ParseIntError, ParseInt);
op3!(std, net, AddrParseError, ParseAddr);

fn parse_serde_json(s: &str) -> Result<serde_json::Value, EagleErr> {
    let value: serde_json::Value = serde_json::from_str(s)?;
    Ok(value)
}

#[cfg(test)]
mod tests {
    use std::fs;
    use serde_json::Value;
    use super::*;

    #[test]
    fn it_works() {
        fn parse_i32(contents: &str) -> Result<i32, EagleErr> {
            let n = contents.trim().parse::<i32>()?;
            Ok(2 * n)
        }
        match parse_i32("s1") {
            Ok(n) => println!("{}", n),
            Err(err) => println!("Error: {:?}", err),
        }
        assert!(parse_i32("11").is_ok());
    }

    #[test]
    fn test_parse_serde_json() -> Result<(), EagleErr> {
        let s = r#"{"eagle": 18}"#;
        let res = parse_serde_json(s);
        assert!(res.is_ok());
        let s = r#"{"eagle": 18"#;
        let res = parse_serde_json(s);
        assert!(res.is_err());
        Ok(())
    }

    #[test]
    fn test_io_err() -> Result<(), EagleErr>{
        let a = fs::write("a/a/a/Cargo.toml", "aaa")
            .map_err(EagleErr::from);
        assert!(a.is_err());
        Ok(())
    }
}
