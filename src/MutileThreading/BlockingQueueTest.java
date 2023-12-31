package MutileThreading;

import javax.swing.plaf.TableHeaderUI;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE =10;
    private static final int SEARCH_THREADS =100;
    private static File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)){
            System.out.print("Enter base directory:");
            String directory = in.nextLine();
            System.out.print("Enter keywords:");
            String keywords = in.nextLine();

            Runnable enumerator = ()->{
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };

            new Thread(enumerator).start();

            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = ()->{
                    try {
                        boolean done = false;
                        while (!done){
                            File file = queue.take();
                            if(file==DUMMY){
                                queue.put(file);
                                done=true;
                            }else {
                                search(file,keywords);
                            }
                        }
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                };
                new Thread(searcher).start();
            }

        }
    }

    public static void enumerate(File directory) throws InterruptedException{
        File[] files = directory.listFiles();
        for (File file : files) {
            if(file.isDirectory()) enumerate(file);
            else queue.put(file);
        }
    }

    public static void search(File file,String keyword) throws IOException{
        try (Scanner in = new Scanner(file,"UTF-8")){
            int lineNumber = 0;
            while (in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword))
                    System.out.printf("%s:%d:%s\n", file.getPath(),lineNumber,line);
            }
        }
    }
}
