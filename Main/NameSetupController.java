package Main;


import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class NameSetupController {
    public Game game;
    @FXML
    public Button backBtn;
    public VBox root;
    public Button addPlayerBtn;
    public Button removePlayerBtn;
    public Button start;
    public Text error;
    private ArrayList<TextField> playerNames = new ArrayList();
    private ArrayList<ImageView> playerImages = new ArrayList();
    private ArrayList<Text> errorTexts = new ArrayList<>();
    private int playerIndex = 0;
    private ArrayList<VBox> PlayerViewList = new ArrayList<>();

    public void setGame(Game game) {
        this.game = game;
    }

    public void initialize() {
        addPlayer();
        addPlayer();
        removePlayerBtn.setVisible(false);
    }


    public void addPlayer() {

        root.getChildren().add(drawPlayerView());
        checkButtons();
    }

    public void removePlayer() {

        root.getChildren().remove(PlayerViewList.get(playerIndex-1));
        PlayerViewList.remove((playerIndex-1));
        playerImages.remove(playerIndex-1);
        playerNames.remove(playerIndex-1);
        errorTexts.remove(playerIndex-1);
        playerIndex-=1;
        checkButtons();

    }

    private void checkButtons() {
        System.out.println(playerIndex);
        if(playerIndex<5){
            addPlayerBtn.setVisible(true);
        }else {
            addPlayerBtn.setVisible(false);
        }
        if (playerIndex> 2) {
            removePlayerBtn.setVisible(true);
        }else {
            removePlayerBtn.setVisible(false);
        }


    }




    private VBox drawPlayerView() {
        playerIndex++;

        // Add Nodes
        VBox playerSetup = new VBox();
        HBox playerView = new HBox();

        TextField playerNameInputField = new TextField();
        playerNameInputField.setPromptText("Player" + playerIndex);
        Button setImageBtn = new Button("set Profile Picture");
        ImageView imageView = new ImageView();
        Image image = new Image("/Resources/Images/sanic.jpg", 80, 80, false, false);
        imageView.setImage(image);
        Text errorText = new Text();
        //set Button on Click event
        setImageBtn.setOnAction((event) -> {
            setPicture(imageView);
        });

        // Layout
        playerView.setAlignment(Pos.CENTER_LEFT);
        playerView.setSpacing(10);
        errorText.setFont(Font.font("System", FontWeight.BOLD, 15));
        errorText.setFill(Color.RED);

        // Add Nodes to Parent
        playerView.getChildren().add(playerNameInputField);
        playerView.getChildren().add(setImageBtn);
        playerView.getChildren().add(imageView);
        playerSetup.getChildren().add(playerView);
        playerSetup.getChildren().add(errorText);

        // add Nodes to Array List
        playerImages.add(imageView);
        playerNames.add(playerNameInputField);
        errorTexts.add(errorText);
        PlayerViewList.add(playerSetup);
        return playerSetup;
    }

    private void setPicture(ImageView playerAvatar) {
        FileChooser dc = new FileChooser();
        dc.setTitle("Choose Avatar");
        dc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg")); // limit fileChooser options to image files
        File selectedFile = dc.showOpenDialog(root.getScene().getWindow());
        if (selectedFile != null) {
            Image imageFile = null;
            try {
                imageFile = new Image(selectedFile.toURI().toURL().toString(), 80, 80, false, false);
                playerAvatar.setImage(imageFile);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        } else {
            error.setText("Image file could not be found");
        }
    }


    private boolean validateUserInput() {

        boolean state = false;

        for (int i = 0; i < playerNames.size(); i++) {
            errorTexts.get(i).setText("");
            if (playerNames.get(i).getText().isEmpty()) {
                errorTexts.get(i).setText("Player " + (i + 1) + " please enter a   Name ");
                state = false;

            } else if (playerNames.get(i).getText().length() > 20 || !Pattern.matches(("^[a-zA-Z0-9 ]+"), playerNames.get(i).getText())) {
                errorTexts.get(i).setText("Name must be between 1 and 20 characters and cannot contain special characters ");
                state = false;

            } else {

                state = true;
            }


        }


        return state;
    }

    @FXML
    public void start() {
        if (validateUserInput()) {
            game.setPlayers(playerIndex, playerNames, playerImages);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/FXML/Game.fxml"));
            try {
                Stage stage;
                stage = (Stage) (start.getScene().getWindow());
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
    }

    public void goBack() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Resources/FXML/GameSetup.fxml"));
        try {
            Stage stage;
            stage = (Stage) (start.getScene().getWindow());
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


