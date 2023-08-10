cmake_minimum_required(VERSION 3.20)
project(conan_demo)

set(CMAKE_CXX_STANDARD 14)
add_executable(conan_demo main.cpp)

find_package(GTest REQUIRED)

# Using the global target name
target_link_libraries(conan_demo gtest::gtest)
target_link_libraries(conan_demo GTest::gmock)
# Component libgtest
target_link_libraries(conan_demo GTest::gtest)
# Component gmock_main
target_link_libraries(conan_demo GTest::gmock_main)
# Component gtest_main
target_link_libraries(conan_demo GTest::gtest_main)