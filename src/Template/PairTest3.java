package Template;

import ClassLearning.Employee;
import ClassLearning.Manager;

public class PairTest3 {
    public static void main(String[] args) {
        Manager ceo = new Manager("John",8000,100);
        Manager cfo = new Manager("Jeck",6000,200);
        Pair<Manager> buddies = new Pair<>(ceo,cfo);
        printBuddies(buddies);

        ceo.setBonus(10000);
        cfo.setBonus(50000);

        Manager[] managers = {ceo,cfo};

        Pair<Employee> result = new Pair<>();
        minmaxBonus(managers,result);
        printMinMax(result);
        maxminBonus(managers,result);
        printMinMax(result);
    }

    public static void printBuddies(Pair<? extends Employee> p){
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName()+"and"+second.getName()+"are buddies");
    }

    public static <T extends Employee> void printMinMax(Pair<T> p){
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println("first:"+first.getName()+" Second:"+second.getName());
    }

    public static void minmaxBonus(Manager[] a,Pair<? super Manager> result){
        if(a.length==0){
            return;
        }
        Manager min = a[0];
        Manager max = a[0];

        for (int i=1; i<a.length;i++){
            if(min.getBonus()>a[i].getBonus()) min = a[i];
            if(max.getBonus()<a[i].getBonus()) max = a[i];
        }

        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxminBonus(Manager[] a,Pair<? super Manager> result){
        minmaxBonus(a,result);
        PairAlg.swap(result);
    }

}

class PairAlg{
    public static boolean hasNulls(Pair<?> p){
        return p.getFirst()==null || p.getSecond()==null;
    }

    public static <T> void swapHelper(Pair<T> p){
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }

    public static void swap(Pair<?> p){
        swapHelper(p);
    }
}
