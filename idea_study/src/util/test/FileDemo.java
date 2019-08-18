package util.test;

import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;

public class FileDemo {

    @Test
    public void fun() throws Exception{
        String path = "D:"+ File.separator+"test"+File.separator+"hty"+File.separator+"ssl"+File.separator+"aabb";
        File file = new File(path+File.separator+"hello.txt");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        PrintWriter out = new PrintWriter(file);
        out.write("hello world xxxx !");
        out.close();
    }
}
