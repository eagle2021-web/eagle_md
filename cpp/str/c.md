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