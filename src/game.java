import javafx.application.Application;
import javafx.event.Event;
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

public class game extends Application implements EventHandler {

    private Stage window;
    private Button dayButton;
    private Scene stockScene;

    private Scene townScene;

    private Label dayLabel;
    private LineChart lineChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private XYChart.Series series;

    private TextField buyInput;
    private TextField sellInput;

    private int dayInt = 0;
    private int stockValue = 10;

    private boolean GDPGrowth = true;

    private int growthChance = 5;
    //growthChance * 10 to get percentage stock will increase each day
    //only takes whole numbers (integers)

    private int growthVolitility = 3;
    // the max amount the stock can jump up

    private int decreaseVolitility = 3;
    // the max amount the stock can jump down

    private int playerMoney = 500;
    //player has starting cash $500

    private int playerStocksOwned = 0;

    private Button buyButton;
    private Button sellButton;
    private Label stockWorthLbl;
    private Label stocksOwnedLbl;
    private Label playerMoneyLbl;

    private Button backToTownButton;
    private VBox townLayout;


    private int nextDay(){
        dayInt += 1;
        return (dayInt);
    }

    private int getDay(){
        return dayInt;
    }

    private int getStockValue(){
        return stockValue;
    }

    private boolean getGDPGrowth(){
        return GDPGrowth;
    }

    private void setRecession(){
        GDPGrowth = false;
    }

    private void setNonRecession(){
        GDPGrowth = true;

    }

    private void setGrowth(){
        if (GDPGrowth==true){
            growthChance = 7;
        } else {
            growthChance = 3;
        }
    }

    private void changeStockValue(){
        if ((int)(Math.random()*10) < growthChance) {
            stockValue += Math.random() * growthVolitility;
        } else if(stockValue<=0){
            //do nothing
        } else {
            stockValue -= Math.random() * decreaseVolitility;
        }
    }

    private int getPlayerMoney(){
        return playerMoney;
    }

    private int getPlayerStocksOwned(){
        return playerStocksOwned;
    }

    private void buyStock(){
        if(getStockValue() * Integer.parseInt(buyInput.getText()) <= getPlayerMoney()){
            playerMoney-= getStockValue() * Integer.parseInt(buyInput.getText());
            playerStocksOwned += Integer.parseInt(buyInput.getText());
        }
    }

    private void sellStock(){
        if (playerStocksOwned >= Integer.parseInt(sellInput.getText())){
            playerMoney += getStockValue() *Integer.parseInt(sellInput.getText());
            playerStocksOwned -= Integer.parseInt(sellInput.getText());
        }
    }




    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("Game");

        dayButton = new Button();
        dayButton.setText("Next Day");
        dayButton.setOnAction(this);

        dayLabel = new Label();
        dayLabel.setText("Day " + String.valueOf(dayInt));

        playerMoneyLbl = new Label("Money: " + getPlayerMoney());
        stocksOwnedLbl = new Label("Stocks: " + getPlayerStocksOwned());


        buyButton = new Button();
        buyButton.setText("Buy Stocks");
        buyButton.setOnAction(this);

        sellButton = new Button();
        sellButton.setText("Sell Stocks");
        sellButton.setOnAction(this);

        buyInput = new TextField();
        sellInput = new TextField();

        backToTownButton = new Button("Back to Town");
        backToTownButton.setOnAction(e -> window.setScene(townScene));

        Label townLbl = new Label("Welcome back to town! Where to go?");
        Button toStocksBtn = new Button("Stock Trade");
        toStocksBtn.setOnAction(e->window.setScene(stockScene));
        townLayout = new VBox(20);
        townLayout.getChildren().addAll(townLbl, toStocksBtn);
        townScene = new Scene(townLayout, 750, 500);
        townLayout.setPadding(new Insets(10,10,10,10));

//        StackPane layout = new StackPane();
//        layout.getChildren().addAll(dayButton, dayLabel);

        xAxis = new NumberAxis(0, 10,1);
        xAxis.setLabel("Days");
        yAxis = new NumberAxis(0,30,1);
        yAxis.setLabel("Value($)");
        lineChart = new LineChart(xAxis,yAxis);
        series = new XYChart.Series();
        series.setName("Stock Value");

        series.getData().add(new XYChart.Data(getDay(),getStockValue()));
        lineChart.getData().add(series);




        GridPane layout = new GridPane();
        layout.setMinSize(750,500);
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);

        layout.add(lineChart,0,0);
        layout.add(dayLabel,0,1);
        layout.add(dayButton,0,2);
        layout.add(backToTownButton,0, 3);

        layout.add(buyInput, 2,1);
        layout.add(sellInput,3,1);
        layout.add(buyButton,2,2);
        layout.add(sellButton,3,2);
        layout.add(playerMoneyLbl,2,3);
        layout.add(stocksOwnedLbl,3,3);




        setNonRecession();
        stockScene = new Scene(layout,750,500);
        primaryStage.setScene(stockScene);
        primaryStage.show();

    }

    @Override
    public void handle(Event event) {
        if(event.getSource() == dayButton){
            dayLabel.setText("Day " + String.valueOf(nextDay()));

            setGrowth();
            changeStockValue();
            series.getData().add(new XYChart.Data(getDay(),getStockValue()));

//
            if(getDay()>=xAxis.getUpperBound()) {
                xAxis.setUpperBound(xAxis.getUpperBound() + 1);
            }
            if(getDay()>=xAxis.getLowerBound()+30){
                xAxis.setLowerBound(xAxis.getLowerBound() + 1);
            }

            if(getStockValue()>=yAxis.getUpperBound()){
                yAxis.setUpperBound(yAxis.getUpperBound() + 1);
            }

            if(dayInt % 100==0){
                setRecession();
            }
            if(dayInt %21==0){
                setNonRecession();
            }
        }
        else if(event.getSource() == buyButton){
            buyStock();
            playerMoneyLbl.setText("Money: " + getPlayerMoney());
            stocksOwnedLbl.setText("Stocks: "+ getPlayerStocksOwned());
        }
        else if(event.getSource()==sellButton){
            sellStock();
            playerMoneyLbl.setText("Money: " + getPlayerMoney());
            stocksOwnedLbl.setText("Stocks: "+ getPlayerStocksOwned());
        }
    }
}
