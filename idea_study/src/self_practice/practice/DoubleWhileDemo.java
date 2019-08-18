package practice;

import java.util.*;
import java.util.stream.Collectors;

class Person {
    private Integer id;
    private String name;
    private Integer familyId;

    public Person(Integer id, String name, Integer familyId) {
        this.id = id;
        this.name = name;
        this.familyId = familyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", familyId=" + familyId +
                '}';
    }
}

class Family {
    private Integer id;
    private String name;
    private List<Person> personList;

    public Family(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    private String getPerson() {
        StringBuilder sb = new StringBuilder();
        /*Iterator<Person> iterator = personList.iterator();
        while (iterator.hasNext()){
            sb.append(iterator.next()+",");
        }*/
        personList.forEach(v -> {
            sb.append(v).append(",");
        });
        return sb.toString().substring(0, sb.length() - 1);
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //  ", personList=" + getPerson() +
                '}';
    }

    public String toString1() {
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personList=" + getPerson() +
                '}';
    }
}

public class DoubleWhileDemo {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(new Person(i, "hty-" + i, i & 3));
        }
        List<Family> families = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            families.add(new Family(i, "hu"));
        }
        list.forEach(System.out::println);
        System.out.println("==============================");
        Map<Integer, Family> map = families.stream().collect(Collectors.toMap(Family::getId, b -> b));
        Map<Integer, List<Family>> listMap = families.stream().collect(Collectors.groupingBy(Family::getId));
        System.out.println(listMap);
        System.out.println(map);
        list.forEach(b -> {
            Family family = map.get(b.getFamilyId());
            if (family.getPersonList() == null) {
                family.setPersonList(new ArrayList<>());
            }
            family.getPersonList().add(b);
        });
        map.values().forEach(b -> {
            System.out.println(b.toString1());
        });
    }
}
