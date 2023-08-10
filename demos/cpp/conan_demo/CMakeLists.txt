cmake_minimum_required(VERSION 3.20)
project(conan_demo)

set(CMAKE_CXX_STANDARD 14)
add_executable(conan_demo main.cpp)

find_package(ZLIB REQUIRED)

# Using the global target name
target_link_libraries(conan_demo ZLIB::ZLIB)
