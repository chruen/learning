package Set;

import java.util.Objects;

public class Item implements Comparable<Item>{
    private String description;
    private int partNumber;

    public Item(String description,int partNumber){
        this.description = description;
        this.partNumber = partNumber;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null) return false;
        if (this.getClass()!=object.getClass()) return false;
        return description.equals(((Item) object).description)&& partNumber==((Item)object).partNumber;
    }

    @Override
    public int compareTo(Item o) {
        int diff = Integer.compare(this.partNumber,o.partNumber);
        return diff!=0?diff:this.description.compareTo(o.description);
    }

    @Override
    public String toString(){
        return "[description=:" + description +", partNumber="+partNumber+"]";
    }

    @Override
    public int hashCode(){
        return Objects.hash(description,partNumber);
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getPartNumber() {
        return partNumber;
    }
}
