package aop;

import reborn.aop.Interceptor;

/**
 * @author Reborn
 * @since 2016/6/25
 */
public class InterDemo extends Interceptor {
    public void before() {
        System.out.println("before");
    }
}
