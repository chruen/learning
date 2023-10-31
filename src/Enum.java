public class Enum {
    public static void main(String[] args) {
        Size[] values = Size.values();
        for(Size v:values){
            System.out.println(v.getA());
        }
        System.out.println(Size.SMALL.ordinal());
        java.lang.Enum.valueOf(Size.class,"SMALL");
        int x = values[0].compareTo(values[1]);
        System.out.println(x);
    }
}


enum Size{
    SMALL("s"),
    MEDIUM("m"),
    LARGE("l");
    private String a;
    private Size(String s){
        this.a = s;
    }
    public String getA(){
        return a;
    }
}