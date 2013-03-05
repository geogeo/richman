import java.util.ArrayList;
import java.util.List;

public class Player {
    private final static String BUY_FIELD_IN_PRICE = "是否购买该处空地，%1$s元（Y/N）?" ;
    private final static String UPGRADE_FIELD_IN_PRICE = "是否升级该处地产，%1$s元（Y/N）?" ;
    private final static String PAY_IN_OTHERS_LAND = "该地归%1$s所有，支付给钱夫人%2$s元";
    private String name;
    private int position = 0;
    private Kami kami=null;
    private int property = 0;
    boolean availability = true;
    public Player(String name,int property) {
        this.name = name;
        this.property = property;
    }

    public void move(int i) {
        position=(position+i)%Rich.length;
    }

    public String promo() {

        Field currentPlace = Rich.MAP[position];
        int charges = currentPlace.charge(this);
        if(currentPlace.owner()==null )return String.format(BUY_FIELD_IN_PRICE, currentPlace.getPrice());
        if(currentPlace.owner()==this) return String.format(UPGRADE_FIELD_IN_PRICE, currentPlace.getPrice());
        if (charges==0)
            return "免过路费";
        else {
            if (kami == null||!kami.available())    {
                property-=charges;
            return String.format(PAY_IN_OTHERS_LAND, currentPlace.owner(), currentPlace.charge(this));
            } else {
                return kami.work();
            }

        }
    }

    public void append(Kami kami) {
        this.kami = kami;
    }

    public int property() {
        return property;
    }

    public String toString() {
        return name;
    }

    public boolean available() {
        return availability;
    }
    public void disable(){
        availability = false;
    }
    public void enable(){
        availability = true;
    }
}
