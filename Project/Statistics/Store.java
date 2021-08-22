package Statistics;

import java.sql.*;

public class Store {

    private String Storename;
    private LinkList Candles = new LinkList();
    public static Connection connection;
    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //attributes
    public Store(String storename){Storename = storename;}
    public String getStoreName(){
        return Storename;
    }
    public void setCandles(Candle candles){
        Candles.add(candles);
    }
    public LinkList candlesFromDB(Store store, String storename){
        try {
            connection = this.connect();
            Statement sta = connection.createStatement();
            String query = "SELECT Code, Quantity, Price FROM Distributions WHERE Store = '" + storename + "';";
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {

                String code = rs.getString("Code");
                String quantity = rs.getString("Quantity");
                int price = rs.getInt("Price");
                int rquantity = Integer.parseInt(quantity);
                store.setCandles(new Candle(price,rquantity,code));
            }


        } catch (SQLException e) {e.printStackTrace();}
        return Candles;

    }
    public Candle storebBestSellingCandle(){
        Candle biggest = Candles.get(0);
        for(int y = 0; y<Candles.Size; y++){
            if (biggest.getQuantity()<Candles.get(y).getQuantity()){
                biggest = Candles.get(y);
            }
        }
        return biggest;
    }
    public Candle storeWorstSellingCandle(){
        Candle smallest = Candles.get(0);
        for(int y = 0; y<Candles.Size; y++){
            if (smallest.getQuantity()>Candles.get(y).getQuantity()){
                smallest = Candles.get(y);
            }
        }
        return smallest;
    }
    public int storeRevenue(){
        int revenue =0;
        for(int y = 0; y<Candles.Size; y++){
            revenue += Candles.get(y).multiplyPQ();
        }
        return revenue;
    }
    public int totalSold(){
        int totalsold =0;
        for(int y = 0; y<Candles.Size; y++){
            totalsold =+ Candles.get(y).getQuantity();
        }
        return totalsold;
    }

}