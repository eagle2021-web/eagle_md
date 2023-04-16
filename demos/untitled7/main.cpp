#include <iostream>
#include "gtest/gtest.h"
using namespace testing;
int main(int argc, char **argv) {
    InitGoogleTest(&argc, argv);
    std::cout << "ok" << std::endl;
    return 0;
}