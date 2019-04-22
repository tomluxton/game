package game;

import game.Model;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class View {

    private Stage primaryStage;
    private Scene currentScene;
    private Scene stockScene;
    private Scene townScene;
    private Scene workScene;

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
    private Button toStocksBtn;
    private Button toWorkBtn;

    private ArrayList<Button> buttonArray;

    private Label stockWorthLbl;
    private Label stocksOwnedLbl;
    private Label playerMoneyLbl;

    private VBox townLayout;
    private VBox workLayout;

    private Model model;


    public void updateScene() {
        dayLabel.setText("Day " + String.valueOf(model.getDay()));
        playerMoneyLbl.setText("Money: " + model.getPlayerMoney());
        stocksOwnedLbl.setText("Stocks: " + model.getPlayerStocksOwned());
        System.out.println(model.getPlayerMoney() + " updateScene end ");

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
        System.out.println(buttonArray);
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



        buyInput = new TextField();
        sellInput = new TextField();
        inputArray = new ArrayList<>();
        inputArray.addAll(Arrays.asList(
                buyInput,
                sellInput));


        Label townLbl = new Label("Welcome back to town! Where to go?");

        townLayout = new VBox(20);
        townLayout.getChildren().addAll(townLbl, toStocksBtn, toWorkBtn);
        townLayout.setPadding(new Insets(10, 10, 10, 10));
        townScene = new Scene(townLayout, 750, 500);


        Label workLbl = new Label("Welcome to work!");
        Button backToTownBtnWork = new Button("Back to Town");

        workLayout = new VBox(20);
        workLayout.getChildren().addAll(workLbl, backToTownBtnWork);
        workLayout.setPadding(new Insets(10, 10, 10, 10));
        workScene = new Scene(workLayout, 750, 500);

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


        updateScene();
        GridPane layout = new GridPane();
        layout.setMinSize(750, 500);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);

        layout.add(lineChart, 0, 0);
        layout.add(dayLabel, 0, 1);
        layout.add(dayButton, 0, 2);
        layout.add(backToTownBtnStock, 0, 3);

        layout.add(buyInput, 2, 1);
        layout.add(sellInput, 3, 1);
        layout.add(buyButton, 2, 2);
        layout.add(sellButton, 3, 2);
        layout.add(playerMoneyLbl, 2, 3);
        layout.add(stocksOwnedLbl, 3, 3);

        buttonArray.addAll(Arrays.asList(dayButton,
                buyButton,
                sellButton,
                backToTownBtnStock,
                toStocksBtn,
                toWorkBtn,
                backToTownBtnWork));


        stockScene = new Scene(layout, 750, 500);
        System.out.println(this.townScene);
        primaryStage.setScene(stockScene);
        primaryStage.show();

    }

    public void set_TownScene() {
        System.out.println(primaryStage);
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
    }
}
