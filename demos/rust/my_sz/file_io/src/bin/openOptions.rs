fn main() {
    let f = OpenOptions::new()   // 一个建造者模式的例子。每个方法都会返回一个OpenOptions结构体的新实例，并且附带相关选项的集合。
        .read(true)   // 为读取而打开文件。
        .write(true)   // 开启写入。这行代码不是必需的，因为后面的append隐含了写入的选项。
        .create(true)   // 如果在path处的文件不存在，则创建一个文件出来。
        .append(true)   // 不会删除已经写入磁盘中的任何内容。
        .open(path)?;   // 打开在path处的文件，然后解包装中间产生的Result。
}