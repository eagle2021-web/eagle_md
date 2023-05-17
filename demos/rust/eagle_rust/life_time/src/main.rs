#![allow(unused)]
fn main() {
    println!("Hello, world!");
}

fn aa<'a, 'b>(s: &'a str, s2: &'b str) -> &'b str
    where 'a: 'b
{
    if s.len() < s2.len() {
        return s;
    }
    return s2;
}

#[cfg(test)]
mod tests {
    use super::aa;

    #[test]
    fn test_a() {
        let s = "sss";
        {
            let s2 = "ssss";
            let _ = aa(s, s2);
        }
    }
}