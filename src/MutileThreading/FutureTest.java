package MutileThreading;

import com.sun.jdi.InternalException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)){
            System.out.print("Enter base directory:");
            String directory = in.nextLine();
            System.out.print("Enter keyword:");
            String keyword = in.nextLine();

            MatchCounter counter = new MatchCounter(new File(directory),keyword);
            FutureTask<Integer> task = new FutureTask<>(counter);
            Thread t = new Thread(task);
            t.start();
            try {
                System.out.println(task.get()+" matching files");
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }catch (InternalException e){

        }
    }
}

class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyword;

    MatchCounter(File directory,String keyword){
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() {
        int count=0;
        try {
            File[] files=directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if(file.isDirectory()){
                    MatchCounter counter = new MatchCounter(file,keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                }
                else {
                    if (search(file)) count++;
                }
            }
            for (Future<Integer> result:results){
                try {
                    count += result.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }catch (InternalException e){
            System.out.println(e);
        }
        return count;
    }

    public boolean search(File file){
        try(Scanner in = new Scanner(file,"UTF-8")){
            boolean found = false;
            while (!found&&in.hasNextLine()){
                String line = in.nextLine();
                if (line.contains(keyword)) found = true;
            }
            return found;
        }catch (IOException e){
            return false;
        }
    }
}

