package reborn.aop;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Reborn
 * @since 2016/6/25
 * 收集aop信息
 */
public final class Scan {

    private static Scan scan = new Scan();
    private static Map<Class,Set<String>> inters = new HashMap<Class, Set<String>>();
    private static Map<String, Interceptor> singleInters = new HashMap<String, Interceptor>();

    private Scan(){}

    public static Scan getScan() {
        return scan;
    }

    public void scanClassInters(Set<Class> css) throws IllegalAccessException, InstantiationException {
        for (Class cs : css) {
            Method[] methods = cs.getDeclaredMethods();
            for (Method method : methods) {
                Before before = method.getAnnotation(Before.class);
                if (before != null) {
                    Set<String> interSet = inters.get(cs);
                    if (interSet == null) {
                        interSet = new HashSet<String>();
                    }
                    interSet.add(method.getName());
                    inters.put(cs, interSet);

                    Class interCs = before.value();
                    Interceptor ins = (Interceptor) interCs.newInstance();
                    singleInters.put(cs.getName() + "." + method.getName(), ins);
                }
            }
        }
    }

    public Map<Class,Set<String>> getInters() {
        return inters;
    }

    public Interceptor getInterByMethod(Class cs, String methodName) {
        return singleInters.get(cs.getName() + "." + methodName);
    }

}
