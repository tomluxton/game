package game;

public class Model {

    private int dayCounter;
    private int stockValue;

    private boolean GDPGrowth;

    private int growthChance;
    //growthChance * 10 to get percentage stock will increase each day
    //only takes whole numbers (integers)

    private int growthVolitility;
    // the max amount the stock can jump up

    private int decreaseVolitility;
    // the max amount the stock can jump down

    private int playerMoney;
    //player has starting cash $500

    private int playerStocksOwned;

    public Model(){
        dayCounter = 0;
        stockValue = 10;

        GDPGrowth = true;

        growthChance = 5;
        //growthChance * 10 to get percentage stock will increase each day
        //only takes whole numbers (integers)

        growthVolitility = 3;
        // the max amount the stock can jump up

        decreaseVolitility = 3;
        // the max amount the stock can jump down

        playerMoney = 500;
        playerStocksOwned = 0;

    }

    public int getDay(){
        return dayCounter;
    }

    public void nextDay(){
        dayCounter += 1;

    }

    public int getStockValue(){
        return stockValue;
    }

    public boolean getGDPGrowth(){
        return GDPGrowth;
    }

    public void setRecession(){
        GDPGrowth = false;
    }

    public void setNonRecession(){
        GDPGrowth = true;
    }

    public void setGrowth(){
        if (GDPGrowth==true){
            growthChance = 7;
        } else {
            growthChance = 3;
        }
    }

    public void changeStockValue(){
        if ((int)(Math.random()*10) < growthChance) {
            stockValue += Math.random() * growthVolitility;
        } else if(stockValue<=0){
            //do nothing
        } else {
            stockValue -= Math.random() * decreaseVolitility;
        }
    }

    public int getPlayerMoney(){
        return playerMoney;
    }

    public int getPlayerStocksOwned(){
        return playerStocksOwned;
    }

    //Checks Player has enough money to buy desired amount
    private void buyStock(int buyAmount) {

        if(getStockValue() * buyAmount <= getPlayerMoney()){
            playerMoney-= getStockValue() * buyAmount;
            playerStocksOwned += buyAmount;
        }else{
            ;// need to throw some kind of error here if its to much
        }
    }

    //Checks Player has enough stocks to sell desired amount
    private void sellStock(int sellAmount) {

        if (playerStocksOwned >= sellAmount) {
            playerMoney += getStockValue() * sellAmount;
            playerStocksOwned -= sellAmount;
        } else {
            ;// need to throw some kind of error here if its to much
        }
    }

}
