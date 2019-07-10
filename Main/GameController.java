package Main;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    public HBox controllButtons;
    public HBox diceView;
    public HBox playerView;
    public VBox startScreen;
    public Button nextPlayerBtn;
    public Button rollBtn;
    public Text playerInfo;
    public ArrayList<VBox> roundpointList = new ArrayList();
    public ArrayList<Text> playerNames = new ArrayList();
    public ArrayList<Text> currentScores = new ArrayList();
    public ArrayList<ImageView> playerImages = new ArrayList();
    Game game;
    private int current = 0;
    private int dice;

    public void setGame(Game gameObject) {
        this.game = gameObject;
        setUpGui();
        game.start();

    }

    public void setUpGui() {
        if (!game.getShowTutorial()) {
            startScreen.setVisible(false);
            rollBtn.setVisible(true);
            drawPlayerInfo();
            nextPlayerBtn.setVisible(false);
        } else {
            controllButtons.setVisible(false);
        }
        for (int i = 0; i < game.getPlayers(); i++) {
            playerView.getChildren().add(addPlayerView(i));
        }
    }

    public void startGame() {
        startScreen.setVisible(false);
        controllButtons.setVisible(true);
        nextPlayerBtn.setVisible(false);
        drawPlayerInfo();
    }

    public void rollDie() {
        current = game.getCurrentPlayer();
        nextPlayerBtn.setText("save Points");
        nextPlayerBtn.setVisible(true);
        nextPlayerBtn.setOnAction(e -> endTurn());
        dice = game.diceRoll();
        drawDice();

        if (game.checkIfTurnEnd()) {
            rollBtn.setVisible(false);
            nextPlayerBtn.setText("Next Player");
        }
    }

    public void endTurn() {
        nextPlayerBtn.setVisible(false);
        drawPlayerPointHistory();
        drawScore();
        game.switchPlayers();
        if (game.checkIfEnd()) {
            loadScoreScene();
        }
        current = game.getCurrentPlayer();
        drawPlayerInfo();
        diceView.getChildren().clear();
        rollBtn.setVisible(true);
    }


    private void drawDice() {
        ImageView diceImage = new ImageView();
        Image image = new Image("/Resources/Images/Dices/" + dice + ".jpg", 120, 120, false, false);
        diceImage.setImage(image);
        diceView.getChildren().add(diceImage);
    }

    private void drawPlayerInfo() {
        playerInfo.setText(game.getPlayerName(current) + " Turn " + (game.getTurn() + 1));
    }

    private void drawScore() {
        game.addPointsToScore();
        currentScores.get(current).setText("Score " + game.getPoints());
    }

    private void drawPlayerPointHistory() {
        HBox playerHistory = new HBox();
        String scoreString = "";
        for (int i = 0; i < game.getPlayerScoreHistory().size(); i++) {
            scoreString = scoreString + game.getPlayerScoreHistory().get(i).toString() + " ";
        }
        Text scoreHistory = new Text("Round " + (game.getTurn() + 1) + "  " + " " + scoreString + " ");
        Text totalOfRound = new Text(game.getCurrentPoints() + " of " + game.getSumOfDice());
        Region spacer = new Region();

        playerHistory.getChildren().add(scoreHistory);
        playerHistory.getChildren().add(spacer);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        playerHistory.getChildren().add(totalOfRound);
        roundpointList.get(current).getChildren().add(playerHistory);
    }

    //generates a HBox per Player with Name Picture and Score text
    private HBox addPlayerView(int i) {
        HBox hbox = new HBox();
        VBox points = new VBox();
        VBox player = new VBox();
        ImageView playerImage;
        Text playerName = new Text();
        VBox roundPoints = new VBox();
        Text currentScore = new Text("Score");


        // fill in Nodes
        playerName.setText(game.getPlayerName(i));
        playerImage = game.getPlayerImage(i);


        // Add Nodes to Array List
        playerNames.add(playerName);
        playerImages.add(playerImage);
        currentScores.add(currentScore);
        roundpointList.add(roundPoints);

        //Ad Nodes to Layout Parent
        points.getChildren().add(roundPoints);
        player.getChildren().add(playerName);
        player.getChildren().add(playerImage);
        player.getChildren().add(currentScore);

        // add Layout Nodes to Parent
        hbox.getChildren().add(points);
        hbox.getChildren().add(player);

        //Layout
        diceView.setPadding(new Insets(15, 12, 15, 12));
        diceView.setSpacing(10);
        playerName.setFont(Font.font("System", FontWeight.BOLD, 15));

        hbox.setAlignment(Pos.CENTER_LEFT);
        player.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setPrefSize(800, 120);
        points.setPrefSize(400, 120);
        player.setPrefSize(400, 120);
        hbox.setStyle("-fx-border-color: black");
        player.setMaxWidth((hbox.getWidth() / 2));

        return hbox;
    }


    private void loadScoreScene() {

        game.getWinner();
        current = 0;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/FXML/ScoreScreen.fxml"));
            Stage stage;
            stage = (Stage) (playerNames.get(current).getScene().getWindow());
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            ScoreScreenController controller = fxmlLoader.getController();
            controller.setGame(game);
            stage.setScene(scene);
            stage.setHeight(900);
            stage.setWidth(1400);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}















