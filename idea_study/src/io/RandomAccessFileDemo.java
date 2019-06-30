package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    private final static String PATH = "C:\\Users\\lenovo\\Desktop\\test.txt";

    public static void main(String[] args) throws Exception {
        RandomAccessFile rafR = new RandomAccessFile(PATH, "r");
        rafR.seek(1);
        String read = rafR.readLine();
        System.out.println(new String(read.getBytes("ISO-8859-1"), "UTF-8"));
        System.out.println(rafR.length());
        rafR.close();

    }
}
