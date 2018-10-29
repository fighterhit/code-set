package design_pattern.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Fighter.
 */
public class StarHandler implements InvocationHandler {

    Star realStar;

    public StarHandler(Star realStar) {
        this.realStar = realStar;
    }

    /**
     * @param proxy  代理
     * @param method 调用的方法
     * @param args   参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = method.invoke(realStar, args);
        return object;
    }
}
