```cpp
#include <iostream>
#include "cstring"

class My_STR {
public:
    static char *cp(char *src) {
        const size_t n = std::strlen(src);
        char *ret = new char[n + 1];
        memset(ret, 0, n);
        strncpy(ret, src, n);
        return ret;
    }

    static void cp(char *const dest, const char *const src, const size_t d_len) {
        if (src == nullptr)return;
        memset(dest, 0, d_len);
        const size_t n = std::strlen(src);
        if (n >= d_len) {
            strncpy(dest, src, d_len - 1);
        } else {
            strncpy(dest, src, n);
        }
    }
};

int main() {
    char *s = "eagle";
    char *s2 = My_STR::cp(s);
    std::cout << (void *) s2 << std::endl;
    std::cout << (void *) s << std::endl;
    std::cout << s2 << std::endl;
    std::cout << std::strlen(s2) << std::endl;
    char *s3 = new char[20];
    std::cout << s3 << std::endl;
    My_STR::cp(s3, s, 20);
    std::cout << s3 << std::endl;
    std::cout << std::strlen(s3) << std::endl;
}
```
```cpp
#include <stdio.h>
#include <string.h>

int main()
{
    char src[40];
    char dest[12];
    memset(dest, 0, sizeof(dest));
    strcpy(src, "This is runoob.com"); // 将后者复制给前者，可能越界
    strncpy(dest, src, 10);//复制10个字符
    printf("最终的目标字符串： %s\n", dest);
    strcpy(dest, "This is runoob.com");
    printf("最终的目标字符串： %s\n", dest);
    return 0;
}
```