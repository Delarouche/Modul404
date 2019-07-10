package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("../Resources/FXML/Start.fxml"));
        primaryStage.setTitle("Dice Game");
        primaryStage.setScene(new Scene(root, 1400, 900));
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/Resources/Images/dicelogo.jpg")));
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
