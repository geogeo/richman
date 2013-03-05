import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RichTest {
    private Rich rich;
    private Player p1;
    private Player p2;

    @Before
    public void setUp(){
        rich = new Rich();
        rich.initMap();
        p1 = new Player("阿土伯",10000);
        p2 = new Player("钱夫人", 10000);
    }

    // 停留在其它玩家的空地或楼房处，需支付相应级别的费用。
    @Test
    public void show_price_100_where_player_in() {

        p1.move(2);
        assertEquals( "是否购买该处空地，100元（Y/N）?",p1.promo())  ;
    }

    @Test
    public void upgrade_where_play_at_his_own_place(){
        Rich.MAP[2].changeOwner(p1);
        p1.move(2);
        assertEquals("是否升级该处地产，100元（Y/N）?",p1.promo());
    }
    // 停留在其它玩家空地处，支付该处空地购买价的1/2
    @Test
    public void should_pay_half_of_other_players_fields_price(){
        Rich.MAP[2].changeOwner(p2);
        Rich.MAP[2].setPrice(100);
        p1.move(2);
        assertEquals("该地归钱夫人所有，支付给钱夫人50元",p1.promo());
        assertEquals(9950,p1.property());
    }

    // 地产每升一级，费用翻倍。
    @Test
    public void shold_pay_double_when_levelup(){
        Rich.MAP[2].changeOwner(p2);
        Rich.MAP[2].setPrice(100);
        Rich.MAP[2].levelup();
        p1.move(2);
        assertEquals("该地归钱夫人所有，支付给钱夫人100元",p1.promo());
        assertEquals(9900,p1.property());
    }

    // 如果福神俯身，可免费，系统提示：“福神附身，可免过路费”；
   @Test
    public void pay_nothing_if_FukuKami_aside(){
       Rich.MAP[2].changeOwner(p2);
       Rich.MAP[2].setPrice(100);
       p1.append(new FukuKami(p1));
       p1.move(2);
       assertEquals("福神附身，可免过路费",p1.promo());
       assertEquals(10000,p1.property());
   }

    //如果地产的主人在医院或监狱中  也免过路费。
    @Test
    public void pay_nothing_if_owner_in_jail_or_hospital() {

        Rich.MAP[2].changeOwner(p2);
        Rich.MAP[2].setPrice(100);
        p2.disable();
        p1.move(2);
        assertEquals("免过路费", p1.promo());
        assertEquals(10000, p1.property());
    }

    //命令：Roll。掷骰子行走1~6步。步数由随即 算法产生。如果途中碰到路障，停留在路障处。 玩家显示的符号覆盖当前位置的地产符号。
    public void roll_when_block_in_the_way(){

    }
}
