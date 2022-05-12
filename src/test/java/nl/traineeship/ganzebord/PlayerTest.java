package nl.traineeship.ganzebord;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    Player player = new Player("Henk");

    @Test
    public void playerNameTest() {
        player.setName("Piet");
        assertTrue("Piet" == player.getName());
    }

    @Test
    public void playerPositionTest() {
        player.setPosition(40);
        assertTrue(40 == player.getPosition());
    }

    @Test
    public void playerSkipTurnTest() {
        player.setSkipTurn(2);
        assertTrue(2 == player.getSkipTurn());
    }
}
