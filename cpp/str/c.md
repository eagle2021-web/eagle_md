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
```CPP
char *STRCPY(char* dest,const size_t destlen,const char* src)
{
  if (dest==0) return 0;
  memset(dest,0,destlen);   // 初始化dest。
  if (src==0) return dest;

  if (strlen(src)>destlen-1) strncpy(dest,src,destlen-1); 
  else strcpy(dest,src);

  return dest;
}
```