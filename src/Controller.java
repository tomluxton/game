package game;

import game.Model;
import game.View;

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
        button.get(6).setOnAction(e -> travel_ToTownPage());
        button.get(7).setOnAction(e -> show_JobListings());
        button.get(8).setOnAction(e -> employed_janitor());
        button.get(9).setOnAction(e -> work_shift());
        button.get(10).setOnAction(e -> travel_ToTownPage()); //backToTownBtnUni
        button.get(11).setOnAction(e -> travel_ToUniPage()); //toUniBtn
        button.get(12).setOnAction(e -> travel_ToUniPage());


        setAbilityOfWork();

    }

    public void work_shift(){
        model.payPlayerWage();
        nextDay();
//        view.updateScene();
    }

    public void employed_janitor(){
        view.hide_jobListings();
        model.setJob("Janitor");
        model.setWage(100);
        view.set_WorkAbility();
        view.set_WorkScene();
    }

    //b means bachelour
    public void enroll_b_business(){
        model.setDegree("bBusiness");
        model.setSemesterCost(5000);
        view.set_UniverstiyScene();
    }

    public void show_JobListings(){
        view.set_jobListings();
    }

    public void setAbilityOfWork(){
        if (model.getPlayersJob()!= "Unemployed"){
            view.set_WorkAbility();
        } else {
            view.remove_WorkAbility();
        }
    }

    private void travel_ToUniPage(){
        view.set_UniverstiyScene();
    }

    private void travel_ToStockPage() {
        view.set_StockScene();
       // view.updateScene();
    }

    private void travel_ToTownPage() {
        view.hide_jobListings();
        view.set_TownScene();
//        System.out.println("travel to town button");

        // view.updateScene();
    }

    private void travel_ToTownPage2(){
        view.hide_jobListings();
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
