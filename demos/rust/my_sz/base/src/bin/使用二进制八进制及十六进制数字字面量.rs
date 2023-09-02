fn main(){
    let three = 0b11;    //  0b前缀表示二进制数字，以2为基数。
    let thirty = 0o36;    //  0o前缀表示八进制数字，以8为基数。
    let three_hundred = 0x12C;    //  0x前缀表示十六进制数字，以16为基数。
    println!("base 10: {} {} {}", three, thirty, three_hundred);
    println!("base 2: {:b} {:b} {:b}", three, thirty, three_hundred);
    println!("base 8: {:o} {:o} {:o}", three, thirty, three_hundred);
    println!("base 16: {:x} {:x} {:x}", three, thirty, three_hundred);
}

