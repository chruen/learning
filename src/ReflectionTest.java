import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {
    public static void main(String[] args) {
        String name="java.lang.Double";

        try {
            Class<?> cl = Class.forName(name);
            Class<?> superCl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length()>0){
                System.out.print(modifiers + " ");
            }
            System.out.print("Class "+name);
            if (superCl!=null && superCl!=Object.class)
                System.out.print(" extends "+superCl.getName());
            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethod(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printConstructors(Class<?> cl){
        Constructor<?>[] constructors = cl.getConstructors();
        for (Constructor<?> constructor:constructors){
            String name = constructor.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(constructor.getModifiers());
            printModifiers(modifiers);
            System.out.print(name+"(");
            printParamTyps(constructor.getParameterTypes());
        }
    }

    public static void printMethod(Class<?> cl){
        Method[] methods = cl.getMethods();

        for(Method method:methods){
            Class<?> retType = method.getReturnType();
            String name = method.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(method.getModifiers());
            printModifiers(modifiers);
            System.out.print(retType.getName()+" "+name+"(");
            printParamTyps(method.getParameterTypes());
        }
    }


    public static void printFields(Class<?> cl){
        Field[] fields = cl.getFields();
        for (Field field:fields){
            Class<?> type = field.getType();
            String name = field.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(type.getModifiers());
            printModifiers(modifiers);
            System.out.println(type.getName()+" "+name+";");
        }
    }

    private static void printParamTyps(Class<?>[] parameterTypes) {
        Class<?>[] paramTypes = parameterTypes;
        for (int j = 0;j<paramTypes.length;j++){
            if(j>0) System.out.print(",");
            System.out.print(paramTypes[j].getName());
        }
        System.out.println(");");
    }

    private static void printModifiers(String modifiers){
        if (modifiers.length()>0){
            System.out.print(modifiers+" ");
        }
    }
}
