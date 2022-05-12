package nl.traineeship.ganzebord;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Dice dice;
    private Board board;
    private ArrayList<Player> players;

    public Game() {
        this.setDice(new Dice());
        this.setBoard(new Board(100));
        this.setPlayers(createPlayerList());
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public void play() {
        printWelcomeBanner();
        handleAGame(getDice(), getBoard(), getPlayers());

        if (board.isGameFinished()) {
            printEndScoreBanner();
            printPlayerStanding(getPlayers());
        }
    }

    private static void handleAGame(Dice dice, Board board, ArrayList<Player> players) {
        aa:
        for (int y = 0; y < board.getTurns(); y++) {
            bb:
            for (Player currentPlayer : players) {
                Scanner inputForDice = new Scanner(System.in);
                System.out.println();
                System.out.println("    " + "Speler " + currentPlayer.getName() + " mag gooien. Druk op enter.");
                boolean hasThrownDice = inputForDice.hasNextLine();
                if (hasThrownDice) {
                    handleATurn(board, dice, currentPlayer);

                    if (board.isGameFinished()) {
                        break aa;
                    }
                } else {
                    System.out.print("Niet gegooid");
                }
            }

            printCurrentScoreBanner();
            printPlayerStanding(players);
        }
    }

    private static void printCurrentScoreBanner() {
        System.out.println();
        System.out.println("***************");
        System.out.println("* TUSSENSTAND *");
        System.out.println("***************");
        System.out.println();
    }

    private static void printEndScoreBanner() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("*   <> EINDSTAND <>   *");
        System.out.println("***********************");
        System.out.println();
    }

    private static void printWelcomeBanner() {
        System.out.println();
        System.out.println("*********************************");
        System.out.println("*           GANZEBORD           *");
        System.out.println("*********************************");
    }

    private static void printPlayerStanding(ArrayList<Player> players) {
        for (Player player : players) {
            System.out.printf("%-15s", player.getName());
            System.out.printf(" | %02d | ", player.getPosition());

            for (int z = 0; z < player.getPosition(); z++) {
                System.out.print("#");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void handleATurn(Board board, Dice dice, Player currentPlayer) {
        int currentTurn = board.doATurn();
        int throwResult = dice.doThrow();
        String playerName = currentPlayer.getName();
        System.out.println("Ronde " + currentTurn);

        int newPlayerPosition = currentPlayer.getPosition() + throwResult;

        if (currentPlayer.getSkipTurn() != 0) {
            currentPlayer.setSkipTurn(currentPlayer.getSkipTurn() - 1);
            System.out.println("    " + playerName + " moet een beurt overslaan.");

            newPlayerPosition = newPlayerPosition - throwResult;
        } else {
            if (throwResult == 12) {
                System.out.println("    " + playerName + " gooit 6, dus wordt 12.");
            } else {
                System.out.println("    " + playerName + " gooit " + throwResult + ".");
            }
            currentPlayer.setSkipTurn(board.isInSkipTurnPosition(newPlayerPosition));
        }

        currentPlayer.setPosition(newPlayerPosition);

        if (board.isBackToStartPosition(newPlayerPosition)) {
            currentPlayer.setPosition(0);
        }

        // FIXME this ruins the game to often
//        if(board.isPrisonPosition(newPlayerPosition)) {
//            board.setGameFinished(true);
//        }

        if (board.isFinishPosition(newPlayerPosition)) {
            board.setGameFinished(true);
        }

        if (board.isOverTheFinishPosition(newPlayerPosition)) {
            int stepsToMany = newPlayerPosition - board.FINISH;
            newPlayerPosition = board.FINISH - stepsToMany;
            currentPlayer.setPosition(newPlayerPosition);
        }

        System.out.println("    " + playerName + " staat op positie " + currentPlayer.getPosition() + ".");
    }

    public ArrayList<Player> createPlayerList() {
        ArrayList<Player> players = new ArrayList<>();

        Scanner inputForPlayerAmount = new Scanner(System.in);
        System.out.println("Hoeveel spelers doen mee? ");
        int amountOfPlayers = inputForPlayerAmount.nextInt();

        for (int i = 0; i < amountOfPlayers; i++) {
            Scanner inputForName = new Scanner(System.in);
            int indexPlayer = i + 1;
            System.out.println("Voer naam op van speler #" + indexPlayer + ": ");
            String name = inputForName.nextLine();
            Player p = new Player(name);
            players.add(p);
        }

        return players;
    }
}
