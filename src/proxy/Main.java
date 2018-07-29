package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        MaotaiJiu maotaiJiu=new MaotaiJiu();

        Wuliangye wuliangye=new Wuliangye();


        InvocationHandler jingxiao1=new GuitaiA(maotaiJiu);
        InvocationHandler jingxiao2=new GuitaiA(wuliangye);
        SellWine dynamicProxy= (SellWine) Proxy.newProxyInstance(MaotaiJiu.class.getClassLoader(),MaotaiJiu.class.getInterfaces(),jingxiao1);
        SellWine dynamicProxy1= (SellWine) Proxy.newProxyInstance(Wuliangye.class.getClassLoader(),Wuliangye.class.getInterfaces(),jingxiao2);
        dynamicProxy.mianJiu();
        dynamicProxy1.mianJiu();

    }
}
