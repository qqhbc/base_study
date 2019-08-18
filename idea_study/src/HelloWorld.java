import java.io.*;
import java.util.HashMap;

public class HelloWorld {
    private static final String PATH = "D:" + File.separator + "update" + File.separator + "test.txt";
    private static final String PICTURE_IN = "D:" + File.separator + "update" + File.separator + "test.png";
    private static final String PICTURE_OUT = "D:" + File.separator + "update" + File.separator + "static" + File.separator + "test.jpg";
    private static int count = 10;

    public static void test() {
        System.out.println("nihao " + ++count);
    }

    public static void main(String[] args) throws IOException {
        System.out.println((short) 2 / 1.2);
        /**
         * 读取文本内容
         */
//        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(PATH))));
//        String s = null;
//        StringBuffer sb = new StringBuffer();
//        while((s = in.readLine()) != null){
//            sb.append(s+"\n");
//        }
//        System.out.println(sb.toString());
//        in.close();
        /**
         * 拷贝文件
         */
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(PICTURE_IN)));
//        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(PICTURE_OUT)));
//        byte [] bytes = new byte[in.available()];
//        while(in.read(bytes) != -1){
//            out.write(bytes);
//        }
//        out.close();
//        in.close();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动下载完成啦！！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
