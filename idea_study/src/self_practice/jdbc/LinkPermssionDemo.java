package self_practice.jdbc;

import jdbc.util.ConnectionUtils;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

class BasPermssion{
    private Long id;
    private Long roleId;
    private Long permssionId;
    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermssionId() {
        return permssionId;
    }

    public void setPermssionId(Long permssionId) {
        this.permssionId = permssionId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "BasPermssion{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permssionId=" + permssionId +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
class BasUserDto{
    private Long userId;
    private Long roleId;
    private String longName;
    private String deleteFlag;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "BasUseDto{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", longName='" + longName + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }
}
public class LinkPermssionDemo {

    public List<BasUserDto> queryAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT a.user_id,a.login_name,a.delete_flag,b.role_id " +
                    "FROM bas_user_role b JOIN bas_user a ON a.user_id = b.user_id";
            connection = ConnectionUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            List<BasUserDto> list = new ArrayList<>();
            while (rs.next()) {
                BasUserDto user = new BasUserDto();
                user.setUserId(rs.getLong(1));
                user.setLongName(rs.getString(2));
                user.setDeleteFlag(rs.getString(3));
                user.setRoleId(rs.getLong(4));
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("heiheihei");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new RuntimeException("hihihi");
            }
        }
    }

    public List<BasPermssion> queryAllPermssion(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id,role_id,permission_id,create_by " +
                    "FROM bas_role_permission";
            connection = ConnectionUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            List<BasPermssion> list = new ArrayList<>();
            while (rs.next()){
                BasPermssion permssion = new BasPermssion();
                permssion.setId(rs.getLong(1));
                permssion.setRoleId(rs.getLong(2));
                permssion.setPermssionId(rs.getLong(3));
                permssion.setCreateBy(rs.getString(4));
                list.add(permssion);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("heiheihei");
        }finally {
            try {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("hihihi");
            }
        }
    }

    public static void main(String[] args) {
        LinkPermssionDemo lpd = new LinkPermssionDemo();
        //全部的用户角色关联数据
        List<BasUserDto> basUserDtoList = lpd.queryAll();
        //全部的角色权限关联数据
        List<BasPermssion> basPermssionList = lpd.queryAllPermssion();
        //假设要删除roleId = 2 ,List<Long> permssionIdList = 1,2
        Long roleId = 2L;
        List<Long> permssionIds = Arrays.asList(1L,2L,3L,4L);
        Set<Long> permssionIdList = new HashSet<>(permssionIds);
        List<Long> deleteIdList = basPermssionList.stream().filter(p -> p.getRoleId().equals(roleId) && permssionIdList.contains(p.getPermssionId()))
                .map(BasPermssion::getId).collect(Collectors.toList());
        Set<Long> roleIdSet;
        Set<String> nameSet = basUserDtoList.stream().filter(b -> b.getRoleId().equals(roleId)).map(BasUserDto::getLongName)
                .collect(Collectors.toSet());
        while (true) {
            roleIdSet = new HashSet<>();
            for (BasPermssion p : basPermssionList) {
                if (!p.getRoleId().equals(roleId) && nameSet.contains(p.getCreateBy()) && permssionIdList.contains(p.getPermssionId())) {
                    deleteIdList.add(p.getId());
                    roleIdSet.add(p.getRoleId());
                }
            }
            if(roleIdSet.size() == 0){
                break;
            }
            nameSet = new HashSet<>();
            for(BasUserDto b : basUserDtoList){
                if(roleIdSet.contains(b.getRoleId())){
                    nameSet.add(b.getLongName());
                }
            }
            if (nameSet.size() == 0){
                break;
            }
        }
        deleteIdList.forEach(System.out::println);

    }
}
