package false_jdbc.utils;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * 获取properties文件内容工具类
 */
public class PropertiesUtils {

    /**
     * 通过key获取properties中的value
     * @param key
     * @return
     */
    public static String getProperty(String key){
        String path = System.getProperty("user.dir")+File.separator+"src"+File.separator;
        String value;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path+"resource"+File.separator+"conf.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            value = (String) properties.get(key);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("not found file");
        } catch (IOException e) {
            throw new RuntimeException("load file error");
        }finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

}
