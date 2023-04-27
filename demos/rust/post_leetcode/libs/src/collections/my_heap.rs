
#[cfg(test)]
mod test_heap {
    use std::collections::BinaryHeap;
    use std::cmp::Reverse;
    #[test]
    fn test_small_root_heap() {
        let mut heap = BinaryHeap::new();
        heap.push(10);
        heap.push(30);
        heap.push(20);
        assert_eq!(heap.peek().unwrap().clone(), 30);
        let a = heap.pop().expect("empty heap");
        assert_eq!(a, 30);
    }
    #[test]
    fn test_push() {
        let mut heap = BinaryHeap::new();
        heap.push(Reverse(10));
        heap.push(Reverse(30));
        heap.push(Reverse(20));
        assert_eq!(*heap.peek().expect("empty heap"), Reverse(10));
        assert_eq!(heap.pop().unwrap().0, 10);
    }
}