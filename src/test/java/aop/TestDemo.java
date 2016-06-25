package aop;

import reborn.aop.Before;

/**
 * @author Reborn
 * @since 2016/6/25
 */
class TestDemo implements ITest {
    @Before(InterDemo.class)
    public void sayHello() {
        System.out.println("msg");
    }

    @Before(InterDemo.class)
    public void sayHello2() {
        System.out.println("sayHello2");
    }
}