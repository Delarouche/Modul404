package Main;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

    private int playerammount;
    private int turnAmmount;
    private int dices;
    private int dice;
    private int current = 0;
    private int roundCount = 0;
    private int turnCount = 0;
    private int amountOfPlayers;
    private int points = 0;
    private boolean showTutorial = true;


    private ArrayList<Player> players = new ArrayList<>();


    public void start() {
        amountOfPlayers = getPlayers();
    }

    public void setPlayers(int playerIndex, ArrayList<TextField> playerNames, ArrayList<ImageView> playerImages) {
        for (int i = 0; i < playerIndex; i++) {
            players.add(new Player(playerNames.get(i).getText(), playerImages.get(i)));
        }
        playerammount = playerIndex;
    }

    public int diceRoll() {
        dice = players.get(current).rollDice();
        return dice;
    }

    public int getTurn() {
        return turnCount;
    }

    public boolean checkIfTurnEnd() {
        if (dice % 2 == 0) {
            points += dice;
            if (roundCount >= getDice()) {
                return true;
            } else {
                roundCount++;
                return false;
            }
        } else if (dice % 2 != 0) {
            points = 0;
            return true;
        }
        return false;
    }

    public void addPointsToScore(){
        players.get(current).addPoints(points);
    }

    public boolean checkIfEnd() {
        boolean end = false;
        if (turnCount >= getTurns()) {
            end = true;

        }
        return end;
    }

    public String getPlayerName(int i) {
        return players.get(i).getName();
    }

    public ImageView getPlayerImage(int i) {
        return players.get(i).getPlayerAvatar();
    }

    public int getCurrentPlayer() {
        return current;
    }

    public ArrayList<Integer> getPlayerScoreHistory() {
        return players.get(current).getDiceList();
    }

    public int getPlayers() {
        return playerammount;
    }

    public int getPlayerScore(int i) {
        return players.get(i).getPoints();
    }

    public int getTurns() {
        return turnAmmount;
    }

    public void setTurns(int turns) {
        turnAmmount = turns;
    }

    public int getDice() {
        return dices;
    }

    public void setDice(int dice) {
        dices = dice;
    }

    public void clear() {
        for (int i = 0; i < playerammount; i++) {
            players.get(i).clearPlayer();
        }
        turnCount = 0;
    }

    public void setTutorial(boolean state) {
        showTutorial = state;
    }

    public boolean getShowTutorial() {
        return showTutorial;
    }


    public void switchPlayers() {

        players.get(current).clear();
        roundCount = 0;
        points = 0;
        if (current < (amountOfPlayers - 1)) {
            current++;
        } else {
            current = 0;
            turnCount++;
        }
    }

    public int getPoints() {
        return players.get(current).getPoints();
    }

    public int getCurrentPoints() {
        return points;
    }

    public int getSumOfDice() {
        int sum = 0;
        for (int i : players.get(current).getDiceList()) {
            sum += i;
        }
        return sum;
    }

    public ArrayList getWinner() {
        Collections.sort(players);
        return players;
    }
}
