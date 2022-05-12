package nl.traineeship.ganzebord;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Dice dice = new Dice();
        Board board = new Board(100);

        ArrayList<Player> players = new ArrayList<>();

        Scanner inputForName = new Scanner(System.in);
        System.out.println("Voer naam op van speler #1: ");
        String name1 = inputForName.nextLine();
        Player player1 = new Player(name1);
        players.add(player1);

        System.out.println("Voer naam op van speler #2: ");
        String name2 = inputForName.nextLine();
        Player player2 = new Player(name2);
        players.add(player2);

        System.out.println("Voer naam op van speler #3: ");
        String name3 = inputForName.nextLine();
        Player player3 = new Player(name3);
        players.add(player3);

        printWelcomeBanner();

        playAGame(dice, board, players);

        if (board.isGameFinished()) {
            printEndScoreBanner();
            printPlayerStanding(players);
        }
    }

    private static void playAGame(Dice dice, Board board, ArrayList<Player> players) {
        aa:
        for (int y = 0; y < board.getTurns(); y++) {
            bb:
            for (Player currentPlayer : players) {
                Scanner inputForDice = new Scanner(System.in);
                System.out.println();
                System.out.println("    " +"Speler " + currentPlayer.getName() + " mag gooien. Druk op enter.");
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
            System.out.println("    " +playerName + " moet een beurt overslaan.");

            newPlayerPosition = newPlayerPosition - throwResult;
        } else {
            if (throwResult == 12) {
                System.out.println("    " +playerName + " gooit 6, dus wordt 12.");
            } else {
                System.out.println("    " +playerName + " gooit " + throwResult + ".");
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

        if(board.isOverTheFinishPosition(newPlayerPosition)) {
            int stepsToMany = newPlayerPosition - board.FINISH;
            newPlayerPosition = board.FINISH - stepsToMany;
            currentPlayer.setPosition(newPlayerPosition);
        }

        System.out.println("    " +playerName + " staat op positie " + currentPlayer.getPosition() + ".");
    }
}
