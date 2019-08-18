package false_jdbc.utils;

import false_jdbc.enums.Gender;
import false_jdbc.model.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGeneratorUtils {
    private static Random random = new Random();
    
    public static List<User> getUsers(int num){
        List<User> list = new ArrayList<>(num);
        for(int i=0;i<num;i++){
            User user  = new User();
            user.setUserName(random.nextBoolean() ? randomLetter(3) : randomLetter(2));
            user.setAccount(random.nextBoolean() ? randomLetter(3)+randomNum(6) : randomLetter(2)+randomNum(6));
            user.setPassword(CryptionUtils.encryption(randomNum(6)));
            user.setAge(Integer.parseInt(randomNum(2)));
            user.setPhone(randomNum(11));
            user.setGender(random.nextBoolean() ? Gender.FEMALE.name() : Gender.MALE.name());
            user.setEmail(randomLetter(2)+randomNum(10)+"@"+randomLetter(3)+".com");
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            list.add(user);
        }
        return list;
    }

    /**
     * 随机获取长度为length的小写字母
     * @param length
     * @return
     */
    private static String randomLetter(int length){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            int letter = random.nextInt(26);
            sb.append((char) (letter + 97));
        }
        return sb.toString();
    }

    private static String randomNum(int length){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            int num = random.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }

}
