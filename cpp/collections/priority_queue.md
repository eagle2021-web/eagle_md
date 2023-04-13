```cpp
#include <iostream>
#include "queue"
using namespace std;
int main(){
    priority_queue<string, vector<string>, greater<>> p;
    p.push("1");
    p.push("2");
    while (!p.empty()){
        cout << p.top() << endl;
        p.pop();
    }
    return 0;
}
```