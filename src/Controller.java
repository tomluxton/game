package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import sun.java2d.loops.ProcessPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
        ArrayList<Button> button;
        button = view.getButtons();


        /*
        Button
        0 = next day
        1 = buy stock
        2 = sellstocks
        3 = travel to town
        4 = travel to stock page
         */
        button.get(0).setOnAction(e -> nextDay());
        button.get(1).setOnAction(e -> buyStocks());
        button.get(2).setOnAction(e -> sellStocks());
        button.get(3).setOnAction(e -> travel_ToTownPage());
        button.get(4).setOnAction(e -> travel_ToStockPage());




    }

    private void travel_ToStockPage() {
        view.set_StockScene();
       // view.updateScene();
    }

    private void travel_ToTownPage() {
        view.set_TownScene();
        System.out.println("travel to town button");

        // view.updateScene();
    }


    private void sellStocks() {

    }

    private void buyStocks() {
    }

    private void nextDay(){
        model.nextDay();
        view.updateScene();
    };

    private void processButton(String text){
        System.out.println(text);

    }

}
