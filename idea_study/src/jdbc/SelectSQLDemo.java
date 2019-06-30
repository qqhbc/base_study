package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Dept {
    private Long id;
    private String name;
    private Long parentId;
    private Integer orderNum;
    private String createBy;
    private List<Dept> childList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public List<Dept> getChildList() {
        return childList;
    }

    public void setChildList(List<Dept> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", orderNum=" + orderNum +
                ", createBy='" + createBy + '\'' +
                ", childList=" + childList +
                '}';
    }
}

public class SelectSQLDemo {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/kitty";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    public static List<Dept> query() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dept> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select id,name,parent_id,order_num,create_by from sys_dept";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dept dept = new Dept();
                dept.setId(resultSet.getLong(1));
                dept.setName(resultSet.getString(2));
                dept.setParentId(resultSet.getLong(3));
                dept.setOrderNum(resultSet.getInt(4));
                dept.setCreateBy(resultSet.getString(5));
                list.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Dept> createTree(List<Dept> list, Long fId) {
        List<Dept> childenList = new ArrayList<>();
        list.forEach(b -> {
            if (b.getParentId() == fId) {
                childenList.add(b);
                b.setChildList(createTree(list, b.getId()));
            }
        });
        return childenList;
    }

    public static List<Dept> menu(List<Dept> list) {
        List<Dept> parentList = list.stream().filter(b -> b.getParentId() == 0).collect(Collectors.toList());
        List<Dept> childenList = list.stream().filter(b -> b.getParentId() != 0).collect(Collectors.toList());
            parentList.forEach(c -> {
                List<Dept> childenList1 = new ArrayList<>();
                childenList.forEach(b -> {
                    if (b.getParentId() == c.getId()) {
                        childenList1.add(b);
                    }
                });
                c.setChildList(childenList1);
                if(c.getChildList() != null){
                    c.getChildList().forEach(d ->{
                        childenList.forEach(e -> {
                            if(e.getParentId() == d.getId()){
                                if(d.getChildList() == null) d.setChildList(new ArrayList<>());
                                d.getChildList().add(e);
                            }
                        });
                    });
                }

            });

        return parentList;
    }

    public static void main(String[] args) throws SQLException {
        List<Dept> list = query();
        if (list != null && list.size() > 0) {
           //  List<Dept> tree = createTree(list, 0l);
            // System.out.println(tree);

//            List<Dept> parentList = list.stream().filter(b -> b.getParentId() == 0).collect(Collectors.toList());
//            List<Dept> childenList = list.stream().filter(b -> b.getParentId() != 0).collect(Collectors.toList());
//            parentList.forEach(b -> {
//                b.setChildList(createTree(childenList,b.getId()));
//            });
//            System.out.println(parentList);
            List<Dept> menu = menu(list);
            System.out.println(menu);
        }
    }
}
