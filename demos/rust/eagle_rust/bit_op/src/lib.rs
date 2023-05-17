#[cfg(test)]
mod test {
    #[test]
    fn test_a() {
        let (a, b) = (1, 3);
        assert_eq!(a & b, 1);
        assert_eq!(a ^ b, 2);
        assert_eq!(a | b, 3);
        // 取最后一个1
        assert_eq!(5 ^ 4  & 7, 1);
        // 去掉最后一个1
        assert_eq!(6 & 5, 4);
    }
}