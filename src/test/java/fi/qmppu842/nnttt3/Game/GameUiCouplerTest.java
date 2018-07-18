package fi.qmppu842.nnttt3.Game;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Qmppu842
 */
public class GameUiCouplerTest {

    private GameUiCoupler coupler;

    public GameUiCouplerTest() {
    }

    @BeforeMethod
    public void setUp() {
        coupler = new GameUiCoupler();
    }

//    Dang... TDD breaks now since I don't know that well what I plan to do...
//    public void jotainTest() {
////        coupler.addMoreAiLevels();
//        coupler.selectPlayer1(1);
//        assertEquals(coupler.);
//
//    }

}
