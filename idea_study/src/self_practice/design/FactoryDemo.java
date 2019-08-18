package design;

interface Fruit {
    void eat();
}

interface FruitFactory {
    Fruit getFruit();
}

class Banana implements Fruit {
    @Override
    public void eat() {
        System.out.println("剥皮着吃！！");
    }
}

class Apple implements Fruit {

    @Override
    public void eat() {
        System.out.println("洗着吃！！");
    }
}

class BananaImplFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Banana();
    }
}

class AppleImpleFactory implements FruitFactory {
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}

class Factories {
    public static <T> T getInstance(Class<T> type) {
        T t = null;
        try {
            t = type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}

public class FactoryDemo {
    public static void main(String[] args) {
        AppleImpleFactory factory = Factories.getInstance(AppleImpleFactory.class);
        Fruit fruit = factory.getFruit();
        fruit.eat();
    }
}
