package reborn.aop;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Reborn
 * @since 2016/6/25
 *
 * 用于配置哪些类需要aop
 */
public class Config {
    private Config(){}
    private static Config cf = new Config();
    private Set<Class> interSet = new HashSet<Class>();

    public static Config getCf() {
        return cf;
    }

    public Set<Class> getInterSet() {
        return interSet;
    }
    public Config add(Class cs) {
        interSet.add(cs);
        return this;
    }
}
