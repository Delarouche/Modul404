package Main;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameSetupController {
    // public Nodes  for  SceneBuilder
    public Text errorRounds;
    public Text errorDice;
    public HBox roundBox;
    public HBox diceBox;
    //validate Textfield inputs to only numbers
    public TextField roundsAmmount = new TextField() {
        @Override
        public void replaceText(int start, int end, String text) {
            if (text.matches("[0-9]*")) {
                super.replaceText(start, end, text);
            }
        }

        @Override
        public void replaceSelection(String text) {
            if (text.matches("[0-9]*")) {
                super.replaceSelection(text);
            }
        }
    };
    //validate Textfield inputs to only numbers
    public TextField diceAmmount = new TextField() {
        @Override
        public void replaceText(int start, int end, String text) {
            if (text.matches("[0-9]*")) {
                super.replaceText(start, end, text);
            }
        }

        @Override
        public void replaceSelection(String text) {
            if (text.matches("[0-9]*")) {
                super.replaceSelection(text);
            }
        }
    };
    // Create new Game Object to store values: game length and dice per turn
    Game game = new Game();
    private boolean animationIsPlaying;
    private boolean animationDiceIsPlaying;


    //add the Textfields to the HBox parent on Load of The FXML
    public void initialize() {
        //Layout
        roundsAmmount.setMaxWidth(30);
        diceAmmount.setMaxWidth(30);

        roundBox.getChildren().add(roundsAmmount);
        diceBox.getChildren().add(diceAmmount);
    }

    // checks if user input  if valid and loads new FXML file for name setup
    @FXML
    private void start() {
        TranslateTransition errorDiceShake = new TranslateTransition();
        TranslateTransition errorRoundsShake = new TranslateTransition();

        errorDiceShake.setNode(diceAmmount);
        errorRoundsShake.setNode(roundsAmmount);

        errorDiceShake.setByX(-10);
        errorRoundsShake.setByX(-10);

        errorDiceShake.setDuration(Duration.millis(100));
        errorRoundsShake.setDuration(Duration.millis(100));

        errorDiceShake.setCycleCount(2);
        errorRoundsShake.setCycleCount(2);

        errorDiceShake.setAutoReverse(true);
        errorRoundsShake.setAutoReverse(true);

        errorRounds.setText("");
        errorDice.setText("");
        if (roundsAmmount.getText().isEmpty() ||
                diceAmmount.getText().isEmpty() ||
                Integer.parseInt(roundsAmmount.getText()) <= 0 ||
                Integer.parseInt(diceAmmount.getText()) <= 0 ||
                Integer.parseInt(roundsAmmount.getText()) > 10 ||
                Integer.parseInt(diceAmmount.getText()) > 8

        ) {
            if (roundsAmmount.getText().isEmpty()) {
                roundsAmmount.setText("");
                errorRounds.setText("Please enter amount of Rounds");
                errorRoundsShake.setOnFinished(e -> setAnimationIsPlaying(false));
                if (!animationIsPlaying) {
                    errorRoundsShake.play();
                    setAnimationIsPlaying(true);
                }
            } else if (Integer.parseInt(roundsAmmount.getText()) <= 1 || Integer.parseInt(roundsAmmount.getText()) > 10  || roundsAmmount.getText().length() > 1)  {
                roundsAmmount.setText("");
                errorRounds.setText("Amount of Rounds must be between 2 and 10");
                errorRoundsShake.setOnFinished(e -> setAnimationIsPlaying(false));
                if (!animationIsPlaying) {
                    errorRoundsShake.play();
                    setAnimationIsPlaying(true);
                }
            }
            if (diceAmmount.getText().isEmpty()) {
                diceAmmount.setText("");
                errorDice.setText("Please enter amount of  max Dice per Round");
                errorDiceShake.setOnFinished(e -> setAnimationDiceIsPlaying(false));
                if (!animationDiceIsPlaying) {
                    errorDiceShake.play();
                    setAnimationDiceIsPlaying(true);
                }
            } else if (diceAmmount.getText().isEmpty() && Integer.parseInt(diceAmmount.getText()) <= 1 || Integer.parseInt(diceAmmount.getText()) > 8|| roundsAmmount.getText().length() > 1) {
                diceAmmount.setText("");
                errorDice.setText("Amount of dice per round must be between 2 and 8");
                errorDiceShake.setOnFinished(e -> setAnimationDiceIsPlaying(false));
                if (!animationDiceIsPlaying) {
                    errorDiceShake.play();
                    setAnimationDiceIsPlaying(true);
                }
            }
        } else {
            int diceAmount = Integer.parseInt(diceAmmount.getText());
            int roundAmount = Integer.parseInt(roundsAmmount.getText());
            game.setTurns(roundAmount);
            game.setDice(diceAmount);

            // Load new FXMl File
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/FXML/NameSetup.fxml"));
            try {
                Stage stage;
                stage = (Stage) (roundsAmmount.getScene().getWindow());
                Parent root = fxmlLoader.load();
                //loads controller of nameSetup FXML to pass Game Object with values
                NameSetupController controller = fxmlLoader.getController();
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
    }


    public boolean setAnimationIsPlaying(boolean setState) {
        animationIsPlaying = setState;
        return animationIsPlaying;

    }
    public boolean setAnimationDiceIsPlaying(boolean setState) {
        animationDiceIsPlaying = setState;
        return animationDiceIsPlaying;

    }
}
