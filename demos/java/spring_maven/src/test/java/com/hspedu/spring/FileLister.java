package com.hspedu.spring;

import java.io.File;

public class FileLister {
    public static void main(String[] args) {
        // 指定要列出所有文件的目录
        File dir = new File("E:/projects/md/eagle_md/demos/java/spring/lib");

        // 调用 listFiles 方法获取目录下的所有子文件和子目录
        File[] files = dir.listFiles();

        // 遍历所有子文件和子目录，如果是文件就输出文件名，如果是目录就递归调用 listFiles 方法
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            } else if (file.isDirectory()) {
                listAllFiles(file);
            }
        }
    }

    // 递归函数：如果参数为目录，则继续调用 listFiles 方法；如果参数为文件，则输出文件名
    private static void listAllFiles(File file) {
        if (file.isFile()) {
            System.out.println("文件名：" + file.getName());
            return;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            listAllFiles(f);
        }
    }
}
