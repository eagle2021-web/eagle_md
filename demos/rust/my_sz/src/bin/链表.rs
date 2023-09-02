use std::rc::{Rc, Weak};
use std::cell::RefCell;
use std::ops::Deref;
use clap::ArgAction::Help;

// 节点定义
#[derive(Debug)]
pub struct Node {
    pub value: i32,
    // 强引用
    pub next: Option<Rc<RefCell<Node>>>,
    // 弱引用
    pub prev: Option<Weak<RefCell<Node>>>,
}

impl Node {
    fn new(value: i32) -> Rc<RefCell<Self>> {
        Rc::new(RefCell::new(Node {
            value,
            next: None,
            prev: None,
        }))
    }
}
#[derive(Debug)]
struct List {
    pub head: Option<Rc<RefCell<Node>>>,
    pub tail: Option<Rc<RefCell<Node>>>,
}

impl List {
    fn new() -> Rc<RefCell<Self>> {
        let head = Node::new(0);
        let tail = Node::new(0);
        // 让node_a引用node_b
        head.borrow_mut().next = Some(Rc::clone(&tail));
        // 让node_b弱引用node_a
        tail.borrow_mut().prev = Some(Rc::downgrade(&head));
        Rc::new(RefCell::new(
            List {
                head: Some(head),
                tail: Some(tail),
            }
        ))
    }
    pub fn append(&mut self, value: i32) {
        let tail_borrow = self.tail.as_ref()
            .unwrap();;
        let tail_br = tail_borrow.borrow()
            .prev
            .as_ref()
            .unwrap()
            .upgrade();

        let tail_prev = tail_br.unwrap();
        let mut new_node = Node::new(value);
        new_node.borrow_mut().next = Some(Rc::clone(tail_borrow));
        tail_borrow.borrow_mut().prev = Some(Rc::downgrade(&new_node));

        tail_prev.borrow_mut().next = Some(Rc::clone(&new_node));
        new_node.borrow_mut().prev = Some(Rc::downgrade(&tail_prev));
    }
    // pub fn print(&mut self) {
    //     let mut rc = self.head.clone();
    //     while true {
    //
    //         match rc.eq(&self.tail) {
    //             true => {
    //                 println!("111");
    //             }
    //             false => {
    //                 println!("111");
    //             }
    //         }
    //         if rc.is_none() {
    //             break;
    //         }
    //         let cur = rc.unwrap();
    //         let next = cur.borrow();
    //         let next2 = next.next.clone();
    //         rc = rc.unwrap()
    //     }
    // }
}
fn main() {
    // 创建两个节点
    let list = List::new();
    println!("{:#?}", list);
    list.borrow_mut().append(10);
    println!("{:#?}", list);
    list.borrow_mut().append(110);
    println!("{:#?}", list);
}