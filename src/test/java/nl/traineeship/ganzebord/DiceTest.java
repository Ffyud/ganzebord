package nl.traineeship.ganzebord;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {

    Dice dice = new Dice();

    @Test
    public void testSetThrowResult() {
        dice.setThrowResult(5);
        assertEquals(5, dice.getThrowResult());
    }

    @Test
    public void testDice() {
        assertTrue(dice.doThrow() >= 1);
        assertTrue(dice.doThrow() <= 12);
    }
}
