//
// Created by chenw on 2023/4/16.
//

#include <gtest/gtest.h>
#include "iostream"
#include "mytest.h"
using namespace testing;
//int add(int a, int b){
//    return a + b;
//}
//
//TEST(AddFunctionTest, PositiveNumbers) {
//EXPECT_EQ(add(2, 3), 5);
//}
//
//TEST(AddFunctionTest, NegativeNumbers) {
//EXPECT_EQ(add(-2, -3), -5);
//}
int main(int argc, char **argv) {
    InitGoogleTest(&argc, argv);
    std::cout << "test_main" << std::endl;
    return RUN_ALL_TESTS();
}