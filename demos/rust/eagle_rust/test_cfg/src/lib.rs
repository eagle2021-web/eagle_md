use std::env;
use std::path::PathBuf;

pub fn project_root() -> PathBuf {
    let path = env::current_dir().unwrap();
    path.parent().unwrap().to_path_buf()
}
#[cfg(test)]
mod test {
    use std::env;
    use crate::project_root;

    #[test]
    fn test_a() {
        let path = project_root();
        println!("The current directory is {}", path.display());
    }
}
