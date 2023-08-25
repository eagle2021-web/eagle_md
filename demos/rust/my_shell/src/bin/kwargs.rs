use std::env;

fn main() {
    env::args().for_each(|x|{
        println!("        self.{} = kwargs.get(\"{}\")", &x, &x);
    });
}