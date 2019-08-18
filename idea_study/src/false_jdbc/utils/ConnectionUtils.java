package false_jdbc.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jdbc连接工具类
 */
public class ConnectionUtils {
    public static Connection getConnection(Map<String,String> map){
        Connection connection = null;
        String sign = CryptionUtils.decryption(PropertiesUtils.getProperty("sign"));
        try {
            Class.forName(CryptionUtils.decryption(PropertiesUtils.getProperty("test.driver")).substring(sign.length()));
           // String url = CryptionUtils.decryption(PropertiesUtils.getProperty("test.url")).substring(sign.length());
            String url = CryptionUtils.decryption(PropertiesUtils.getProperty("localhost.test.url")).substring(sign.length());
            if(map != null && map.size() != 0){
                boolean flag = false;
                StringBuilder sb = new StringBuilder();
                for(Map.Entry entry : map.entrySet()){
                    if(!flag){
                        sb.append("?").append(entry.getKey())
                                .append("=").append(entry.getValue());
                        flag = true;
                    }else {
                        sb.append("&").append(entry.getKey())
                        .append("=").append(entry.getValue());
                    }
                }
                url += sb.toString();
            }
            String user = CryptionUtils.decryption(PropertiesUtils.getProperty("test.user")).substring(sign.length());
            String password = CryptionUtils.decryption(PropertiesUtils.getProperty("test.password")).substring(sign.length());
            connection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("not found driver class");
        } catch (SQLException e) {
            throw  new RuntimeException("connection error");
        }
        return connection;
    }

    public static Connection getConnection(){
       return getConnection(null);
    }
}
