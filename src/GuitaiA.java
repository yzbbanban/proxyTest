import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by brander on 2018/7/29
 */
public class GuitaiA implements InvocationHandler {
    private Object pingpai;

    public GuitaiA(Object pingpai) {
        this.pingpai = pingpai;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("销售开始 柜台是： "+this.getClass().getSimpleName());
        method.invoke(pingpai,args);
        System.out.println("销售结束");
        return null;
    }

}
