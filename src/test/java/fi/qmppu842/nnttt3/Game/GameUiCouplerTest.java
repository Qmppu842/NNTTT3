package fi.qmppu842.nnttt3.Game;

import fi.qmppu842.nnttt3.Game.profiles.RandomProfile;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test
    public void nonHumanTurnTest(){
        coupler.selectPlayer1(0);
        coupler.selectPlayer2(0);
//        coupler.setPlayer1(new RandomProfile);
        coupler.setUpNewGame();
        coupler.noHumans();
        assertTrue(coupler.getWinner() != 8);
    }
}
