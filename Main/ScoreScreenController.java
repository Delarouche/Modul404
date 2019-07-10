package Main;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class ScoreScreenController {
    public Game game;

    public VBox playerView;


    private int i = 0;

    public void setGame(Game gameObject) {
        this.game = gameObject;
        displayScores();
    }


    private void displayScores() {
        for (int i = 0; i < game.getPlayers(); i++) {
            playerView.getChildren().add(addPlayer(i));
        }


    }

    public HBox addPlayer(int i) {

        HBox player = new HBox();
        ImageView playerImage;
        Text playerName = new Text();
        Text score = new Text();
        Text placement = new Text();

        // fill in Nodes
        if (i == 0) {
            placement.setText("1st");
            placement.setFont(Font.font("System", FontWeight.BOLD, 45));
            playerName.setFont(Font.font("System", FontWeight.BOLD, 35));
            placement.setFill(Color.GOLD);
        } else if (i == 1) {
            placement.setText("2nd");
            placement.setFont(Font.font("System", FontWeight.BOLD, 35));
            playerName.setFont(Font.font("System", FontWeight.BOLD, 30));
            placement.setFill(Color.SILVER);
        } else if (i == 2) {
            placement.setText("3rd");
            placement.setFont(Font.font("System", FontWeight.BOLD, 25));
            playerName.setFont(Font.font("System", FontWeight.BOLD, 25));
            placement.setFill(Color.ROSYBROWN);
        } else {
            placement.setText((i + 1) + "th ");
            placement.setFont(Font.font("System", FontWeight.BOLD, 20));

        }


        playerImage = game.getPlayerImage(i);
        playerName.setText(game.getPlayerName(i));
        score.setText(" " + game.getPlayerScore(i) + " Points");


        //Ad Nodes to Layout Parent
        player.getChildren().add(placement);
        player.getChildren().add(playerName);
        player.getChildren().add(playerImage);
        player.getChildren().add(score);


        //Layout
        player.setPadding(new Insets(15, 12, 15, 12));
        player.setSpacing(20);
        player.setAlignment(Pos.CENTER);
        playerName.setFont(Font.font("System", FontWeight.BOLD, 20));
        score.setFont(Font.font("System", FontWeight.BOLD, 20));

        return player;
    }

    public void restartWithNames() {
        game.clear();
        game.setTutorial(false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/FXML/Game.fxml"));
        try {
            Stage stage;
            stage = (Stage) (playerView.getScene().getWindow());
            Parent root = fxmlLoader.load();
            GameController controller = fxmlLoader.getController();
            controller.setGame(game);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(900);
            stage.setWidth(1400);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restart() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/FXML/GameSetup.fxml"));
        try {
            Stage stage;
            stage = (Stage) (playerView.getScene().getWindow());
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(900);
            stage.setWidth(1400);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void endGame() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/FXML/Start.fxml"));
        try {
            Stage stage;
            stage = (Stage) (playerView.getScene().getWindow());
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(900);
            stage.setWidth(1400);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
