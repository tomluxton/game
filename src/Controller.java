package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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
    private ArrayList<Button> button;
    private ArrayList<TextField> textFields;


    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
        button = view.getButtons();
        textFields = view.getTextFields();

        button.get(0).setOnAction(e -> nextDay());
        button.get(1).setOnAction(e -> buyStocks());
        button.get(2).setOnAction(e -> sellStocks());
        button.get(3).setOnAction(e -> travel_ToTownPage());
        button.get(4).setOnAction(e -> travel_ToStockPage());
        button.get(5).setOnAction(e -> travel_ToWorkPage());
        button.get(6).setOnAction(e -> travel_ToTownPage2());

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

    private void travel_ToTownPage2(){
        view.set_TownScene2();
    }

    private void travel_ToWorkPage(){
        view.set_WorkScene();
    }


    private void sellStocks() {
        model.sellStock(Integer.parseInt(textFields.get(1).getText()));
        view.updateScene();

    }

    private void buyStocks() {
        model.buyStock(Integer.parseInt(textFields.get(0).getText()));
        view.updateScene();
    }

    private void nextDay(){
        model.nextDay();
        model.changeStockValue();
        view.updateScene();
    };

    private void processButton(String text){
        System.out.println(text);

    }

}
