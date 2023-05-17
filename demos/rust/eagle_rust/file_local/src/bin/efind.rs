use std::env;

#[path = "../common/mod.rs"]
mod common;

fn main() {
    let arg = env::args().nth(1).expect("at least one arg");
    efind(&arg);
}

pub fn efind(arg: &str) {
    let data = common::data::data();
    let data_obj = data.as_object().unwrap();
    match data_obj.get(arg) {
        None =>  panic!("{}", format!("no key named {}", &arg)),
        Some(a) => {
            println!("{}", a.as_str().unwrap());
        }
    }
}

#[cfg(test)]
mod tests {
    use crate::efind;

    #[test]
    #[should_panic(expected = "no key named hosts2")]
    fn test_efind() {
        efind("hosts2");
    }
}