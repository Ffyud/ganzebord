package nl.traineeship.ganzebord;

import java.util.Random;

public class Dice {

    private int throwResult;
    private final int resultRange = 6;
    private final int resultMinimum = 1;

    public int getThrowResult() {
        return throwResult;
    }

    public void setThrowResult(int throwResult) {
        this.throwResult = throwResult;
    }

    public int doThrow() {
        Random random = new Random();
        int randomResult = random.nextInt(resultRange) + resultMinimum;

        if(randomResult == 6) {
            randomResult = randomResult*2;
        }

        setThrowResult(randomResult);

        return randomResult;
    }
}
