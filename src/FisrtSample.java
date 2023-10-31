import java.time.*;
import static java.lang.Math.*;
import static java.util.Objects.hash;

public class FisrtSample {
    public static void main(String[] args) {
        System.out.println("Hello world");

        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today-1);

        DayOfWeek weekday = date.getDayOfWeek();

        int value = weekday.getValue();

        System.out.println("Mon  Tue  Wed  Thu  Fri  Sat  Sun");
        for (int i=1;i<value;i++){
            System.out.print("     ");
        }
        while(date.getMonthValue()==month){
            System.out.printf("%3d",date.getDayOfMonth());
            if (date.getDayOfMonth()==today){
                System.out.print("* ");
            }else {
                System.out.print("  ");
            }
            date = date.plusDays(1);
            if(date.getDayOfWeek().getValue()==1){
                System.out.println();
            }
        }
        if(date.getDayOfWeek().getValue()!=1){
            System.out.println();
        }
        date.hashCode();
        String s = "00001001";
        int x = Integer.parseInt(s,2);
        System.out.println(x);


    }
}


