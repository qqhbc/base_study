package test.dao;

import false_jdbc.dao.UserDao;
import false_jdbc.enums.Gender;
import false_jdbc.model.User;
import false_jdbc.utils.CryptionUtils;
import false_jdbc.utils.UserGeneratorUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class UserDaoTest {
    private UserDao userDao = new UserDao();
    @Test
    public void insert(){
        User user = new User();
        user.setUserName("hty");
        user.setAccount("hty6923658");
        user.setPassword(CryptionUtils.encryption("123456"));
        user.setAge(33);
        user.setPhone("18230526550");
        user.setGender(Gender.FEMALE.name());
        user.setEmail("1162454662@qq.com");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        System.out.println(user);
        int insert = userDao.insert(user);
        assert insert == 1;
    }

    @Test
    public void batchInsert(){
        long l2 = 0;
        for(int i=0;i<20;i++){
            long l = System.currentTimeMillis();
            List<User> users = UserGeneratorUtils.getUsers(10000);
            userDao.batchInsert(users);
            l2 += System.currentTimeMillis()-l;
        }
        System.out.println(l2);
    }
}
