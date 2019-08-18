package design;

import java.util.*;

interface Observer {
    void add(Obclient obclient);

    void remove(Obclient obclient);

    void publisher(String msg);
}

interface Obclient {
    String getName();

    void subcriber(String msg);
}

class ObserverImpl implements Observer {
    private Set<Obclient> set = new HashSet<>();
    private String msg;

    @Override
    public void add(Obclient obclient) {
        set.add(obclient);
        System.out.println("注册了一个 " + obclient.getName());
    }

    @Override
    public void remove(Obclient obclient) {
        Iterator<Obclient> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(obclient.getName())) {
                iterator.remove();
                break;
            }
        }
        System.out.println(obclient.getName() + " 取消的订阅");
    }

    @Override
    public void publisher(String msg) {
        System.out.println("服务器发布了：" + msg);
        Iterator<Obclient> iterator = set.iterator();
        while (iterator.hasNext()) {
            iterator.next().subcriber(msg);
        }
    }
}

class ObClientImpl implements Obclient {
    private String name;

    public ObClientImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void subcriber(String msg) {
        System.out.println(name + " 订阅了 " + msg);
    }
}

public class ObClientDemo {
    public static void main(String[] args) {
        Observer observer = new ObserverImpl();
        observer.add(new ObClientImpl("胡天耀"));
        observer.add(new ObClientImpl("王鹏旬"));
        observer.add(new ObClientImpl("苏福林"));
        observer.publisher("今天天气真好");
        System.out.println("==============================");
        observer.remove(new ObClientImpl("胡天耀"));
        observer.publisher("有点意思 哈哈哈");

    }
}
