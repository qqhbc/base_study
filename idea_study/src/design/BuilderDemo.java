package design;

class Person {
    private String name;
    private String origin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}

interface Builder {
    void sing(String name);

    void dance(String origin);

    Person builder();
}

class CXKBuilder implements Builder {
    private Person person = new Person();

    @Override
    public void sing(String name) {
        System.out.println(name + "我喜欢唱歌！");
        person.setName(name);
    }

    @Override
    public void dance(String origin) {
        System.out.println("来自 " + origin + " 的舞蹈");
        person.setOrigin(origin);
    }

    @Override
    public Person builder() {
        return person;
    }
}

class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void beginBuilder(String name, String origin) {
        builder.sing(name);
        builder.dance(origin);
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        Builder builder = new CXKBuilder();
        Director director = new Director(builder);
        director.beginBuilder("hty", "湖南");
        Person builder1 = builder.builder();
        System.out.println(builder1);
    }
}
