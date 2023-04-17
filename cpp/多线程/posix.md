pthread_create()
该函数创建一个新线程。以下是使用 pthread_create() 函数创建一个简单的线程并启动的示例代码。
```cpp
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

void *thread_routine(void *arg) {
    printf("New thread created.\n");
    return NULL;
}

int main() {
    pthread_t tid;
    if (pthread_create(&tid, NULL, &thread_routine, NULL)) {
        fprintf(stderr, "Error creating thread.\n");
        return EXIT_FAILURE;
    }
    pthread_join(tid, NULL); //等待新建的线程结束
    return EXIT_SUCCESS;
}

```

pthread_mutex_init(), pthread_mutex_lock() 和 pthread_mutex_unlock()
这些函数用于互斥地管理共享数据。以下是一个简单的使用这三个函数的示例：
```cpp
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER; // 静态初始化互斥量

void *thread_routine(void *arg) {
    pthread_mutex_lock(&mutex);
    printf("Thread ID: %ld has acquired lock.\n", pthread_self());
    // 在这里执行对共享数据的写操作
    printf("Thread ID: %ld has released lock.\n", pthread_self());
    pthread_mutex_unlock(&mutex);
    return NULL;
}

int main() {
    pthread_t threads[2];
    for (int i = 0; i < 2; i++) {
        if (pthread_create(&threads[i], NULL, &thread_routine, NULL)) {
            fprintf(stderr, "Error creating thread.\n");
            return EXIT_FAILURE;
        }
    }
    for (int i = 0; i < 2; i++) {
        pthread_join(threads[i], NULL); //等待新建的线程结束
    }
    return EXIT_SUCCESS;
}

```

pthread_cond_init(), pthread_cond_wait() 和 pthread_cond_signal()
这些函数用于同步线程之间的活动。以下是一个简单的使用这三个函数的示例：
```cpp
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int done = 0;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

void *thread_routine(void *arg) {
    pthread_mutex_lock(&mutex);
    printf("Thread waiting.\n");
    while (!done) { // 等待条件
        pthread_cond_wait(&cond, &mutex);
    }
    printf("Thread finished.\n");
    pthread_mutex_unlock(&mutex);
    return NULL;
}

int main() {
    pthread_t tid;
    if (pthread_create(&tid, NULL, &thread_routine, NULL)) {
        fprintf(stderr, "Error creating thread.\n");
        return EXIT_FAILURE;
    }
    sleep(5); // 主线程睡眠5秒钟
    done = 1; // 修改共享的条件变量
    pthread_cond_signal(&cond); // 唤醒正在等待该条件变量的线程
    pthread_join(tid, NULL); //等待新建的线程结束
    return EXIT_SUCCESS;
}

```