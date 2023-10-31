import java.lang.reflect.*;
import java.util.*;
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[100];

        //填充代理类
        for(int i=0;i<elements.length;i++){
            Integer value = i+1;
            //针对Integer新建代理类
            InvocationHandler handler = new TraceHandle(value);
            //代理了Comparable接口，除此之外，Object类中的所有方法都会被代理，包括（toString、equals、hashCode）
            Object proxy = Proxy.newProxyInstance(null,new Class[]{Comparable.class},handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length)+1;

        int result = Arrays.binarySearch(elements,key);

        if(result>=0) System.out.println(elements[result]);
    }
}

class TraceHandle implements InvocationHandler{
    private Object target;
    public TraceHandle(Object t){
        this.target = t;
    }

    //代理、调用前所做的事情
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);

        System.out.print("."+method.getName()+"(");

        if(args!=null){
            for(int i=0;i<args.length;i++){
                System.out.print(args[i]);
                if(i<args.length-1) System.out.print(", ");
            }
        }
        System.out.println(")");
        return method.invoke(target,args);
    }
}