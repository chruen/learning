package MutileThreading;

import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class ForkJoinTest {

}

class Counter extends RecursiveTask<Integer>{
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filer;

    @Override
    protected Integer compute() {
        return null;
    }
}
