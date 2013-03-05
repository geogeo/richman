public class Rich {
    public final static int length = 72;
     public static Field[] MAP = new Field[72];
    public void initMap(){
        for(int i=0;i<MAP.length;i++) {
            MAP[i] = new Field();
        }
    }
}
