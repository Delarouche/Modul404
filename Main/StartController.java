package Main;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class StartController {


    @FXML
    private Node imageLeft;
    @FXML
    private Node imageRight;


    @FXML
    protected void initialize() {
        //Creating Translate Transition
        TranslateTransition translateTransition1 = new TranslateTransition();
        TranslateTransition translateTransition2 = new TranslateTransition();

        //Setting the duration of the transition
        translateTransition1.setDuration(Duration.millis(1000));
        translateTransition2.setDuration(Duration.millis(1000));

        //Setting the node for the transition
        translateTransition1.setNode(imageLeft);
        translateTransition2.setNode(imageRight);

        //Setting the value of the transition along the x axis.
        translateTransition1.setByY(-55);
        translateTransition2.setByY(-55);


        //Setting the cycle count for the transition
        translateTransition1.setCycleCount(500000);
        translateTransition2.setCycleCount(500000);

        //Setting auto reverse value to false
        translateTransition1.setAutoReverse(true);
        translateTransition2.setAutoReverse(true);


        //Playing the animation
        translateTransition1.play();
        translateTransition2.play();

    }

    @FXML
    public void start() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) (imageLeft.getScene().getWindow());
        root = FXMLLoader.load(getClass().getResource("../Resources/FXML/GameSetup.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(900);
        stage.setWidth(1400);
        stage.show();

    }


}
