package game;

import game.Model;
import game.View;
import game.Controller;
import javafx.application.Application;
import javafx.stage.Stage;


public class main extends Application {

    public void start(Stage primaryStage){

        primaryStage.setTitle("Financial Simulator");
        Model model = new Model();

        View view = new View(model, primaryStage);
        Controller controller = new Controller(view, model);


        primaryStage.show();

    }


}
