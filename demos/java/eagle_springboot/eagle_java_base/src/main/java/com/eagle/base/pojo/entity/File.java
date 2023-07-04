package com.eagle.base.pojo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Data
class FileHash implements Comparable<FileHash>{
    private String alg;
    private String value;

    @Override
    public int compareTo(FileHash o) {
        return Comparator.comparing(FileHash::getAlg)
                .thenComparing(FileHash::getValue)
                .compare(this, o);
    }
}
@Data
public class File implements Comparator<File> {
    private String name;
    private ArrayList<FileHash> hashes;
    @Override
    public int compare(File o1, File o2) {
        return Comparator.comparing(File::getName)
                .compare(o1, o2);
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(11,1,23));
        integers.sort(Integer::compareTo);
        System.out.println(integers);
    }
}
