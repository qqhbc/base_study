package design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface RealBuss {
    void run();
}

class RealBussImpl implements RealBuss {

    @Override
    public void run() {
        System.out.println("我要开始跑步了！！");
    }
}

class DynimacProxy implements InvocationHandler {
    private Object target;

    public DynimacProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备开始");
        Object invoke = method.invoke(target);
        System.out.println("跑步结束");
        return invoke;
    }
}

public class DynimacProxyDemo {
    public static void main(String[] args) {
        RealBussImpl realBuss = new RealBussImpl();
        DynimacProxy proxy = new DynimacProxy(realBuss);
        RealBuss o = (RealBuss) Proxy.newProxyInstance(realBuss.getClass().getClassLoader(), realBuss.getClass().getInterfaces(), proxy);
        o.run();
    }
}
