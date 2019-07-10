package Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Player implements Comparable<Player> {

    private ArrayList<Integer> diceList = new ArrayList<>();

    private String name;
    private ImageView image;


    private int dice;
    private int sum;

    public Player(String playerName, ImageView avatar) {
        if (playerName.isEmpty()) {
            name = "player";
        }
        this.name = playerName;

        if (avatar == null) {
            image.setImage(new Image("/Resources/Images/sanic.jpg", 80, 80, false, false));
        }
        this.image = avatar;
    }

    public int getPoints() {
        return sum;
    }

    public void clearPlayer() {
        sum = 0;

    }

    public ArrayList<Integer> getDiceList() {
        return diceList;
    }

    public void addPoints(int points) {
        sum += points;
    }

    public void clear() {
        diceList.clear();
    }

    public int rollDice() {
        dice = (int) (Math.random() * 6 + 1);
        diceList.add(dice);
        return dice;
    }

    public String getName() {
        return name;
    }

    public ImageView getPlayerAvatar() {
        return image;
    }

    @Override
    public int compareTo(Player compareplayer) {
        int compareSum = compareplayer.getPoints();
        /* For Ascending order */
        //return this.sum-compareSum;
        /* For Descending order */
        return compareSum - this.sum;
    }
}
