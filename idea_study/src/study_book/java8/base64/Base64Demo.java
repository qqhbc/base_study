package java8.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * 3中编码格式  基本 url mime
 */
public class Base64Demo {
    public static void main(String[] args) {

        int [] a = {1,2,6,8,7,9,2,5,6,6,6};
        int [] b = {22,11,55,99,77,33,66,72};
        System.arraycopy(a,1,b,2,6);
        for(int i=0;i<b.length;i++){
            System.out.print(b[i]+"、");
        }
        System.out.println("==============================");
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+"、");
        }


        try {
            String s = Base64.getEncoder().encodeToString("hty is a pig".getBytes("UTF-8"));
            System.out.println("创建基本编码：" + s);
            byte[] decode = Base64.getDecoder().decode(s);
            System.out.println("原始密码：" + new String(decode, "UTF-8"));
            System.out.println("创建url编码：" + Base64.getUrlEncoder().encodeToString("hty is a pig".getBytes("UTF-8")));
            System.out.println("创建mime编码：" + Base64.getMimeEncoder().encodeToString("hty is a pig".getBytes("UTF-8")));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(UUID.randomUUID());
            }
            String mimeString = Base64.getMimeEncoder().encodeToString(sb.toString().getBytes("UTF-8"));
            System.out.println(mimeString);
            String urlString = Base64.getUrlEncoder().encodeToString(sb.toString().getBytes("UTF-8"));
            System.out.println(urlString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
