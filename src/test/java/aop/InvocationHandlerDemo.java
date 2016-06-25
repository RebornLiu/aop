package aop;

import reborn.aop.Before;
import reborn.aop.Interceptor;
import reborn.aop.Scan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author Reborn
 * @since 2016/6/25
 */
class InvocationHandlerDemo implements InvocationHandler {

    private Object target;

    InvocationHandlerDemo(Object o) {
        this.target = o;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Map<Class, Set<String>> interCollection = Scan.getScan().getInters();
        if (interCollection != null) {
            Set<String> methods = interCollection.get(target.getClass());
            if (methods.contains(method.getName())) {
                Before before = target.getClass().getDeclaredMethod(method.getName()).getAnnotation(Before.class);
                if (before != null) {
                    Interceptor inter = Scan.getScan().getInterByMethod(target.getClass(), method.getName());
                    inter.before();
                }
            }
        }

        return method.invoke(target, objects);
    }
}
