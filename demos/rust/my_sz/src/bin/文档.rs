//! Simulating files one step at a time.    //  //! 表示当前语言项的文档，即编译器刚刚进入的那个模块。
/// Represents a "file",
/// which probably lives on a file system.     //  /// 用于注解其后紧跟着的语言项。
#[derive(Debug)]
pub struct File{
    name: String,
    data: Vec<u8>,
}
impl File{
    /// New files are assumed to be empty, but a name is required.
    pub fn new(name: &str) -> File{
        File{
            name: String::from(name),
            data: Vec::new(),
        }
    }
    /// Returns the file's length in bytes.
    pub fn len(&self) -> usize{
        self.data.len()
    }
    /// Returns the file's name.
    pub fn name(&self) -> String{
        self.name.clone()
    }
}
impl File{
    /// Creates a new, empty 'File'.
    ///
    /// # Examples
    ///
    /// ```
    /// let f = File::new2("f1.txt");
    /// ```
    pub fn new2(name: &str) -> File{
        File{
            name: String::from(name),
            data: Vec::new(),
        }
    }
}
/// 如果此包有很多依赖包，这个构建过程可能会耗费较长的时间。一个有用的开关项是cargo doc --no-deps。加上--no-deps，能够明显地限制rustdoc要完成的工作量。
fn main(){

    let f1 = File::new("f1.txt");
    let f1_name = f1.name();
    let f1_length = f1.len();
    println!("{:?}", f1);
    println!("{} is{} bytes long", f1_name, f1_length);
}