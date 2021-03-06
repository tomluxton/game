package game;

import game.Model;
import game.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class View {

    private Stage primaryStage;
    private Scene currentScene;
    private Scene stockScene;
    private Scene townScene;
    private Scene workScene;
    private Scene universtiyScene;

    private Label dayLabel;
    private LineChart lineChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private XYChart.Series series;

    private TextField buyInput;
    private TextField sellInput;

    private ArrayList<TextField> inputArray;


    private Button dayButton;
    private Button buyButton;
    private Button sellButton;
    private Button backToTownBtnStock;
    private Button backToTownBtnWork;
//    private Button backToTownBtnUni;
    private Button toStocksBtn;
    private Button toWorkBtn;
    private Button toUniBtn;

    private ArrayList<Button> buttonArray;

    private Label stockWorthLbl;
    private Label stocksOwnedLbl;
    private Label playerMoneyLbl;

    private Label townMoneyLbl;
    private Label townDayLbl;

    private Label workMoneyLbl;
    private Label workDayLbl;

    private VBox townLayout;
    private VBox workLayout;

    private Model model;

    private HBox workHbox;
    private VBox workVBox;

    private VBox workVBoxSecond;


    private Button lookForWorkBtn;
    private Button workBtn;

    private Button janitorJobBtn;

    private Label stockMarketHboxDayLbl;
    private Label stockMarketHboxMoneyLbl;

    private Label uniDayLbl;
    private Button lookForDegreeBtn;
    private Button studyBtn;

    private VBox uniVBox;
    private VBox uniVBoxSecond;
    private HBox uniHbox;

    private Label uniMoneyLbl;



    public void updateScene() {
        dayLabel.setText("Day " + String.valueOf(model.getDay()));
        playerMoneyLbl.setText("Money: " + model.getPlayerMoney());
        stocksOwnedLbl.setText("Stocks: " + model.getPlayerStocksOwned());
        System.out.println(model.getPlayerMoney() + " updateScene end ");

        townDayLbl.setText("Day " + String.valueOf(model.getDay()));
        townMoneyLbl.setText("Money: " + model.getPlayerMoney());

        workDayLbl.setText("Day " + String.valueOf(model.getDay()));
        workMoneyLbl.setText("Money: " + model.getPlayerMoney());

        stockMarketHboxDayLbl.setText("Day " + String.valueOf(model.getDay()));
        stockMarketHboxMoneyLbl.setText("Money: " + model.getPlayerMoney());

        series.getData().add(new XYChart.Data(model.getDay(), model.getStockValue()));

        if (model.getDay() >= xAxis.getUpperBound()) {
            xAxis.setUpperBound(xAxis.getUpperBound() + 1);
        }
        if (model.getDay() >= xAxis.getLowerBound() + 30) {
            xAxis.setLowerBound(xAxis.getLowerBound() + 1);
        }

        if (model.getStockValue() >= yAxis.getUpperBound()) {
            yAxis.setUpperBound(yAxis.getUpperBound() + 1);
        }

    }

    public ArrayList<Button> getButtons() {
//        System.out.println(buttonArray);
        return buttonArray;
    }

    public ArrayList<TextField> getTextFields() {
        return inputArray;
    }

    public View(Model model, Stage primaryStage) {

        buttonArray = new ArrayList<>();

        this.primaryStage = primaryStage;

        this.model = model;

        dayLabel = new Label();
        stockWorthLbl = new Label();
        stocksOwnedLbl = new Label();
        playerMoneyLbl = new Label();

        dayButton = new Button("Next Day");
        buyButton = new Button("Buy Stocks");
        sellButton = new Button("Sell Stocks");
        backToTownBtnStock = new Button("Back to Town");
        //backToTownButton.setOnAction(e -> window.setScene(townScene));
        toStocksBtn = new Button("Stock Trade");
        //toStocksBtn.setOnAction(e -> window.setScene(stockScene));
        toWorkBtn = new Button("Work");
        toUniBtn = new Button("University");



        buyInput = new TextField();
        sellInput = new TextField();
        inputArray = new ArrayList<>();
        inputArray.addAll(Arrays.asList(
                buyInput,
                sellInput));


        //town scene
        Label townLbl = new Label("Welcome back to town! Where to go?");
        Label townTitleLbl = new Label("Town");
        townDayLbl = new Label("Day " + String.valueOf(model.getDay()));
        townMoneyLbl = new Label("Money: " + model.getPlayerMoney());

        BorderPane townBP = new BorderPane();
        VBox townVBox = new VBox();
        HBox townHBox = new HBox();
        townBP.setTop(townHBox);
        townBP.setCenter(townVBox);

        townHBox.setPadding(new Insets(15, 12, 15, 12));
        townHBox.setSpacing(10);
        townHBox.setStyle("-fx-background-color: #84abff;");

        townVBox.setPadding(new Insets(10));
        townVBox.setSpacing(8);

        townHBox.getChildren().addAll(townTitleLbl, townDayLbl,townMoneyLbl);
        townVBox.getChildren().addAll(townLbl, toStocksBtn, toWorkBtn, toUniBtn);

//        townLayout = new VBox(20);
//        townLayout.getChildren().addAll(townLbl, toStocksBtn, toWorkBtn);
//        townLayout.setPadding(new Insets(10, 10, 10, 10));
        townScene = new Scene(townBP, 750, 600);


        //work scene
        Label workLbl = new Label(model.getJobWelcoming());
        workDayLbl = new Label("Day " + String.valueOf(model.getDay()));
        Button backToTownBtnWork = new Button("Back to Town");
        lookForWorkBtn = new Button(model.getJobHunt());
        workBtn = new Button("Work for the day");



        BorderPane workBP = new BorderPane();
        workVBox = new VBox(20);
        workHbox = new HBox();
        workVBoxSecond = new VBox(20);

        workHbox.setPadding(new Insets(15, 12, 15, 12));
        workHbox.setSpacing(10);
        workHbox.setStyle("-fx-background-color: #84abff;");

        workDayLbl = new Label("Day " + String.valueOf(model.getDay()));
        workMoneyLbl = new Label("Money: " + model.getPlayerMoney());
        Label workTitleLbl = new Label("Work");
        workHbox.getChildren().addAll(workTitleLbl, workDayLbl, workMoneyLbl);

        workBP.setTop(workHbox);
        workBP.setLeft(workVBox);
        workBP.setRight(workVBoxSecond);
        workVBox.getChildren().addAll(workLbl, workBtn, lookForWorkBtn, backToTownBtnWork);
        workVBox.setPadding(new Insets(10));
        workVBox.setSpacing(8);

        workVBoxSecond.setPadding(new Insets(10));
        workVBoxSecond.setSpacing(8);

        janitorJobBtn = new Button("Janitor | Pay: $100 per shift");



        //workVBox.setPadding(new Insets(10, 10, 10, 10));
        workScene = new Scene(workBP, 750, 600);



        //        StackPane layout = new StackPane();
        //        layout.getChildren().addAll(dayButton, dayLabel);

        xAxis = new NumberAxis(0, 10, 1);
        xAxis.setLabel("Days");
        yAxis = new NumberAxis(0, 30, 1);
        yAxis.setLabel("Value($)");
        lineChart = new LineChart(xAxis, yAxis);
        series = new XYChart.Series();
        series.setName("Stock Value");
        series.getData().add(new XYChart.Data(model.getDay(), model.getStockValue()));
        lineChart.getData().add(series);

        //university

        Label uniLbl = new Label("Welcome to University!");
        Button backToTownBtnUni = new Button("Back to town");

        //the following is to be added
        uniDayLbl = new Label("Day " + String.valueOf(model.getDay()));
        lookForDegreeBtn = new Button(model.getDegreeHunt());
        studyBtn = new Button("Study for the day");



        BorderPane uniBP = new BorderPane();
        uniVBox = new VBox(20);
        uniHbox = new HBox();
        uniVBoxSecond = new VBox(20);

        uniBP.setTop(uniHbox);
        uniBP.setLeft(uniVBox);
        uniBP.setRight(uniVBoxSecond);

        uniHbox.setPadding(new Insets(15, 12, 15, 12));
        uniHbox.setSpacing(10);
        uniHbox.setStyle("-fx-background-color: #84abff;");

        uniDayLbl = new Label("Day " + String.valueOf(model.getDay()));
        uniMoneyLbl = new Label("Money: " + model.getPlayerMoney());
        Label uniTitleLbl = new Label("University");
        uniHbox.getChildren().addAll(uniTitleLbl, uniDayLbl, uniMoneyLbl);


        uniVBox.getChildren().addAll(uniLbl, studyBtn, lookForDegreeBtn, backToTownBtnUni);
        uniVBox.setPadding(new Insets(10));
        uniVBox.setSpacing(8);

        uniVBoxSecond.setPadding(new Insets(10));
        uniVBoxSecond.setSpacing(8);

        Button businessDegreeBtn = new Button("Bachelor of Business | Cost per Semester: $5000");

        universtiyScene = new Scene(uniBP, 750, 600);


        //stock market
        BorderPane stockMarketBP = new BorderPane();
        GridPane layout = new GridPane();
        layout.setMinSize(750, 600);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);

        layout.add(lineChart, 0, 0);
//        layout.add(dayLabel, 0, 1);
        layout.add(dayButton, 0, 1);
        layout.add(backToTownBtnStock, 0, 2);

        layout.add(buyInput, 2, 1);
        layout.add(sellInput, 3, 1);
        layout.add(buyButton, 2, 2);
        layout.add(sellButton, 3, 2);
        layout.add(playerMoneyLbl, 2, 3);
        layout.add(stocksOwnedLbl, 3, 3);


        //Button Array at the bottom so all button creation can be done before sending it to controller
        buttonArray.addAll(Arrays.asList(dayButton,
                buyButton,
                sellButton,
                backToTownBtnStock,
                toStocksBtn,
                toWorkBtn,
                backToTownBtnWork,
                lookForWorkBtn,
                janitorJobBtn,
                workBtn,
                backToTownBtnUni, //10
                toUniBtn));

        HBox stockMarketHbox = new HBox();

        stockMarketHbox.setPadding(new Insets(15, 12, 15, 12));
        stockMarketHbox.setSpacing(10);
        stockMarketHbox.setStyle("-fx-background-color: #84abff;");

        stockMarketHboxDayLbl = new Label("Day " + String.valueOf(model.getDay()));
        stockMarketHboxMoneyLbl = new Label("Money: " + model.getPlayerMoney());
        Label stockMarketTitleLbl = new Label("Stock Trade");
        stockMarketHbox.getChildren().addAll(stockMarketTitleLbl, stockMarketHboxDayLbl, stockMarketHboxMoneyLbl);

        stockMarketBP.setTop(stockMarketHbox);
        stockMarketBP.setCenter(layout);
        stockScene = new Scene(stockMarketBP, 750, 600);
//        System.out.println(this.townScene);
        primaryStage.setScene(stockScene);
        primaryStage.show();
        updateScene();

    }

    public void set_jobListings() {
        //add all stuff
        if (workVBoxSecond.getChildren().isEmpty()) {
            Label jobListings = new Label("Here are the jobs listed today:");
            workVBoxSecond.getChildren().addAll(jobListings, janitorJobBtn);

        }
        if (model.getPlayersJob() == "Janitor"){
            workVBoxSecond.getChildren().remove(janitorJobBtn);
        }
    }

    public void hide_jobListings(){
        workVBoxSecond.getChildren().removeAll(workVBoxSecond.getChildren());
    }

    public void set_WorkAbility(){
        Label workLbl = new Label(model.getJobWelcoming());
        workVBox.getChildren().remove(0);
        workVBox.getChildren().add(0, workLbl);
        workVBox.getChildren().add(1, workBtn);

    }

    public void remove_WorkAbility(){
        workVBox.getChildren().remove(workBtn);
    }

    public void set_TownScene() {
//        System.out.println(primaryStage);
        this.primaryStage.setScene(this.townScene);
    }

    public void set_TownScene2(){
        this.primaryStage.setScene(this.townScene);
    }

    public void set_StockScene() {
        primaryStage.setScene(this.stockScene);
    }

    public void set_WorkScene(){
        primaryStage.setScene(this.workScene);
        System.out.println("reached work");
    }

    public void set_UniversityScene(){
        primaryStage.setScene(this.universtiyScene);
        System.out.println("reached uni");
    }
}
