trait Foo where Self: Sized {
    fn foo(&self);
}

impl Foo for i32 {
    fn foo(&self) {
        println!("{}", self);
    }
}

trait Foo2 {
    fn foo1(&self);
    fn foo2(&self) where Self:Sized;
}

impl Foo2 for i32 {
    fn foo1(&self) {
        println!("foo1 {}", self);
    }

    fn foo2(&self) where Self: Sized {
        println!("foo2 {}", self);
    }
}


fn main() {
    println!("Hello, world!");
    let x = 1_i32;
    x.foo();

    // let p = &x as &dyn Foo; // `Foo` cannot be made into an object
    // p.foo(); //调用失败
    // | trait Foo where Self: Sized {
    //     |       ---             ^^^^^ ...because it requires `Self: Sized`
    //     |       |
    //     |       this trait cannot be made into an object...

    println!("Hello2, world!");
    let x = 1_i32;
    x.foo1();
    x.foo2(); // ok

    let p = Box::new(&x as &dyn Foo2);
    p.foo1();
    // p.foo2(); // err
    // error: the `foo2` method cannot be invoked on a trait object
    // --> e_trait\trait_safe\src\main.rs:46:7
    //     |
    //     13 |     fn foo2(&self) where Self:Sized;
    // |                               ----- this has a `Sized` requirement
    //     ...
    //     46 |     p.foo2(); // err
    // |       ^^^^                            ----- this has a `Sized` requirement
}
