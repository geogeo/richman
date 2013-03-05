public class Field {
    private Player owner = null;
    private int price =100;
    private int level=0;
    public Player owner(){
        return owner;
    };

    public int price() {
        return (int)Math.pow(2,level)*price;
    }


    public void changeOwner(Player player) {
        owner=player;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public int charge(Player p) {
        if(owner==null||owner==p||!owner.available())
            return 0;
        else{
            return price/2;
        }

    }

    public void levelup() {
        price *= 2;
    }
}
