package false_jdbc.utils;

import org.junit.Test;

import java.util.Base64;

/**
 * 加解密工具类
 */
public class CryptionUtils {

    /**
     * base64基础加密
     * @param key
     * @return
     */
    public static String encryption(String key){
        return Base64.getEncoder().encodeToString(key.getBytes());
    }

    /**
     * base64解密
     * @param key
     * @return
     */
    public static String decryption(String key){
        return new String(Base64.getDecoder().decode(key.getBytes()));
    }

    @Test
    public void fun(){
        String sign = CryptionUtils.decryption(PropertiesUtils.getProperty("sign"));
        System.out.println(CryptionUtils.encryption(sign+PropertiesUtils.getProperty("localhost.test.url")));
        String substring = CryptionUtils.decryption(PropertiesUtils.getProperty("localhost.test.url")).substring(sign.length());
        System.out.println(substring);
    }
}
