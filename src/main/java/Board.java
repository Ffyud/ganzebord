package nl.duo.traineeship.weekopdracht.ganzebord;

public class Board {

    private int turns;
    private int currentTurn = 0;

    private boolean isGameFinished = false;

    public static final int FINISH = 63;

    public Board(int amountOfTurns) {
        this.setTurns(amountOfTurns);
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

    public int getTurns() {
        return turns;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    private void setTurns(int turns) {
        this.turns = turns;
    }

    public int doATurn() {
        this.setCurrentTurn(this.getCurrentTurn() + 1);
        return this.getCurrentTurn();
    }

    public boolean isPrisonPosition(int position) {
        if(position == 23) {
            System.out.println("    Speler is op positie 23 (gevangenis): einde spel!");
            return true;
        } else {
            return false;
        }
    }

    public boolean isBackToStartPosition(int position) {
        if((position == 25) || (position == 45)) {
            System.out.println("    Speler is op positie "+position+": terug naar start!");
            return true;
        } else {
            return false;
        }
    }

    public boolean isFinishPosition(int position) {
        if(position == FINISH) {
            System.out.println("    Speler is precies op positie " + FINISH + " (finish): spel gewonnen!");
            return true;
        } else {
            return false;
        }
    }

    public boolean isOverTheFinishPosition(int position) {
        if(position > FINISH) {
            System.out.println("    Speler is over de finish positie heen: aantal posities te veel ga je terug!");
            return true;
        } else {
            return false;
        }
    }

    public int isInSkipTurnPosition(int position) {
        if(position == 19) {
            System.out.println("    Speler is op positie 19 (herberg): beurt overslaan!");
            return 1;
        } else if (position == 52) {
            System.out.println("    Speler is op positie 52 (gevangenis): 3 beurten overslaan!");
            return 3;
        } else {
            return 0;
        }
    }

}
