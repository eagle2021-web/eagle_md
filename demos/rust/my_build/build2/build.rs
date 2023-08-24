extern crate cc;
/**

在 cc::Build::new().file("src/hello.c").compile("hello") 这一行中，"hello" 是一个编译生成的库文件的名称。

具体而言，cc::Build::compile 方法用于编译源文件，并指定生成的库文件的名称。在这里，通过传递字符串 "hello" 给 compile
方法，你指定了编译生成的库文件的名称为 "hello"。

库文件的类型取决于编译器以及构建目标的不同。一般来说，库文件的扩展名可以是 .a（静态库）或 .so（共享库）等，
具体取决于操作系统和构建配置。

例如，如果你在 Linux 系统上进行构建，那么生成的库文件名称为 "hello"，则编译器会生成名为 libhello.so 的共享库文件。
如果你在 Windows 上进行构建，那么生成的库文件名称为 "hello"，则编译器会生成名为 hello.lib 的静态库文件。

希望这个回答解决了你的疑问！如果还有其他问题，请随时提问。
*/
fn main() {
    // target/debug/build/build2-64d4e6c4ac563059/out/libaaaaaaaaa.a
    // target/debug/build/build2-64d4e6c4ac563059/out/aaaaaaaaa.lib
    // target/debug/build/build2-64d4e6c4ac563059/out/src/hello.o
    cc::Build::new().file("src/hello.c").compile("aaaaaaaaa");
}
