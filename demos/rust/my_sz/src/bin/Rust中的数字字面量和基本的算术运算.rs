fn main(){
    let twenty = 20;    //  如果你没有提供类型，Rust会替你推断出一个类型……
    let twenty_one: i32 = 21;    //  ……显式添加类型注解（i32）……
    let twenty_two = 22i32;    //  ……或者添加类型后缀。
    let addition = twenty + twenty_one + twenty_two;
    println!("{} +{} +{} ={}", twenty, twenty_one, twenty_two, addition);
    let one_million: i64 = 1_000_000;    //  下画线的使用增强了可读性，编译器会忽略这些下画线。数字本身可以执行方法调用。
    println!("{}", one_million.pow(2));
    let forty_twos = [    //  要创建一个数字的数组，使用方括号括起这些数字，而且这些数字必须是同一类型的。
        42.0,    //  浮点数字面量，没有显式给出类型注解。根据上下文环境，这样的浮点数字面量可能被推断为32位或64位类型。此处是32位类型。
        42f32,    //  浮点数字面量，也可以有类型后缀……
        42.0_f32,    //  ……也可以使用下画线。
    ];
    println!("{:02}", forty_twos[0]);    //  数组元素使用数字作为索引，索引从0开始。
}