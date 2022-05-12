package nl.traineeship.ganzebord;

public class Player {

    private String name;
    private int position = 0;
    private int skipTurn = 0;

    public int getSkipTurn() {
        return skipTurn;
    }

    public void setSkipTurn(int skipTurn) {
        this.skipTurn = skipTurn;
    }

    public Player(String name) {
        this.setName(name);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
