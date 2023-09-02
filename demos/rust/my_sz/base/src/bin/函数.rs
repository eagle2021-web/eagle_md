fn main(){
    let needle = 0o204;
    let haystack = [1, 1, 2, 5, 15, 52, 132, 877, 4140, 21147];
    for item in &haystack{    //  在数组haystack中遍历数组元素的引用。
        if *item == needle{    //  *item解引用，然后复制吗
            println!("{}", item);
        }
    }
}