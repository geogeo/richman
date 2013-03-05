public class FukuKami extends Kami{
    Player owner;
    public FukuKami(Player owner) {
        this.owner = owner;
    }
    public String work(){
        return "福神附身，可免过路费";
    }
    public boolean available() {
        return true;
    }
}
