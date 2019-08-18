package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    private final static String PATH = "C:\\Users\\lenovo\\Desktop\\test.txt";

    public static void main(String[] args) throws Exception {
        RandomAccessFile rafR = new RandomAccessFile(PATH, "r");
        rafR.seek(0);
        StringBuilder sb = new StringBuilder();
        while(rafR.read() != -1){
            sb.append(new String(rafR.readLine().getBytes("ISO-8859-1"),"UTF-8"));
            sb.append("\n");
        }
        System.out.println(sb.toString());
        System.out.println(rafR.length());
        rafR.close();

    }
}
