package java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class BasUserRole{
    private Long id;
    private Long roleId;

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
}
public class Demo {
    public static void main(String[] args) {
        BasUserRole basUserRole = new BasUserRole();
        BasUserRole basUserRole1 = new BasUserRole();
        BasUserRole basUserRole2 = new BasUserRole();
        basUserRole.setId(1L);
        basUserRole1.setId(2L);
        basUserRole2.setId(3L);
        basUserRole.setRoleId(1L);
        basUserRole1.setRoleId(1L);
        basUserRole2.setRoleId(1L);
        List<BasUserRole> list = new ArrayList<>();
        list.add(basUserRole);
        list.add(basUserRole1);
        list.add(basUserRole2);
        Map<Long, List<Long>> collect = list.stream()
                .collect(Collectors.groupingBy(BasUserRole::getRoleId,Collectors.mapping(BasUserRole::getId,Collectors.toList())));
        System.out.println(collect);
    }

}
