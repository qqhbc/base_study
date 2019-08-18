package false_jdbc.dao;

import false_jdbc.model.User;
import false_jdbc.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    public int insert(User user){
        Connection connection = ConnectionUtils.getConnection();
        PreparedStatement preparedStatement = null;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `t_user`(")
          .append("`t_user_name`,`t_account`,`t_password`,`t_gender`,`t_age`,`t_phone`,`t_email`,`create_time`,`update_time`)")
          .append(" values(?,?,?,?,?,?,?,?,?)");
        try {
            preparedStatement = connection.prepareStatement(sb.toString());
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getAccount());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getGender());
            preparedStatement.setInt(5,user.getAge());
            preparedStatement.setString(6,user.getPhone());
            preparedStatement.setString(7,user.getEmail());
            preparedStatement.setString(8,user.getCreateTime().toString());
            preparedStatement.setString(9,user.getUpdateTime().toString());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement);
        }
        return count;
    }

    public void batchInsert(List<User> list){
        Map<String,String> map = new HashMap<>();
        /* 解决中文乱码问题 */
        map.put("useUnicode","true");
        map.put("characterEncoding","utf8");
        // 支持预编译
        map.put("useServerPrepStmts","true");
        // 支持批量
        map.put("rewriteBatchedStatements","true");
        Connection connection = ConnectionUtils.getConnection(map);
        PreparedStatement preparedStatement = null;
        try {
            // 关闭事务提交
            connection.setAutoCommit(false);
            StringBuilder sb = new StringBuilder();
            sb.append("insert into `t_user`(")
                    .append("`t_user_name`,`t_account`,`t_password`,`t_gender`,`t_age`,`t_phone`,`t_email`,`create_time`,`update_time`)")
                    .append(" values(?,?,?,?,?,?,?,?,?)");
            preparedStatement = connection.prepareStatement(sb.toString());
            for(User user : list){
                preparedStatement.setString(1,user.getUserName());
                preparedStatement.setString(2,user.getAccount());
                preparedStatement.setString(3,user.getPassword());
                preparedStatement.setString(4,user.getGender());
                preparedStatement.setInt(5,user.getAge());
                preparedStatement.setString(6,user.getPhone());
                preparedStatement.setString(7,user.getEmail());
                preparedStatement.setString(8,user.getCreateTime().toString());
                preparedStatement.setString(9,user.getUpdateTime().toString());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement);
        }
    }

    private void close(Connection connection, PreparedStatement preparedStatement) {
        try {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
