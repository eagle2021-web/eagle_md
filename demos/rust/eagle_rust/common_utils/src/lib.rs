
pub fn add(left: usize, right: usize) -> usize {
    left + right
}
pub fn replace_str(p: &str) -> String {
    p.replace("\\", "/")
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = add(2, 2);
        assert_eq!(result, 4);
    }

    #[test]
    fn test_replace() {
        let s = "d:/eagle_repos/gitee.com/zengyong2020/web-editor-markdown.git\\v1.0.6";
        let s2 = replace_str(s);
        println!("s2 = {:?}", s2);
        
    }
}
