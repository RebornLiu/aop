package aop;

import reborn.aop.Config;
import reborn.aop.Scan;

import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * @author Reborn
 * @since 2016/6/25
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException {

        Set<Class> css = Config.getCf().add(TestDemo.class).getInterSet();
        Scan.getScan().scanClassInters(css);


        ITest test = new TestDemo();
        ITest proxy = (ITest) Proxy.newProxyInstance(
                ITest.class.getClassLoader(),
                new Class[]{ITest.class},
                new InvocationHandlerDemo(test));

        proxy.sayHello();
        proxy.sayHello2();

    }
}

