package Statistics;

import java.sql.*;
import java.util.LinkedList;

public class Distributor {
    //attributes
    private static LinkedList <Store> stores = new LinkedList<>();
    private static Connection connection;
    private static Connection connect() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //methods
    public void storeList(){

    Distributor m = new Distributor();
        try {
            connection = this.connect();
            Statement sta = connection.createStatement();
            String query = "SELECT DISTINCT Store FROM Distributions;";
            ResultSet rs = sta.executeQuery(query);
            System.out.println(query);
            while (rs.next()) {
                String store = rs.getString("Store");
                stores.add(new Store(store));

            }

        }catch (SQLException e){}




}
    public Store getBestSellingStore(){
        //The method starts looking for the biggest by starting the array at zero
        Store biggest = stores.get(0);
        for (Store x: stores){
            //goes through every store
            if(x.storeRevenue()>biggest.storeRevenue()){
                //if the revenue of the curent store is bigger than the revenue of biggest then
                //biggest is now equal to the curren store
                biggest = x;
            }
        }
        return biggest;

    }
    public Candle getBestSellingCandle(){
        Store biggest = stores.get(0);
        for (Store x: stores){
            if(x.storebBestSellingCandle().getQuantity()>biggest.storebBestSellingCandle().getQuantity()){
                biggest = x;
            }
        }
        return biggest.storebBestSellingCandle();
    }
    public Store getworstSellingStore(){
        Store smallest = stores.get(0);
        for (Store x: stores){
            if(x.storeRevenue()<smallest.storeRevenue()){
                smallest = x;
            }
        }
        return smallest;
    }
    public Candle getWorstSellingCandle(){
        Store smallest = stores.get(0);
        for (Store x: stores){
            if(x.storeWorstSellingCandle().getQuantity()<smallest.storeWorstSellingCandle().getQuantity()){
                smallest = x;
            }
        }
        return smallest.storeWorstSellingCandle();
    }
    public int getRevenue(){
        int revenue =0;
        for(Store x: stores){
           revenue =+ x.storeRevenue();
        }
        return revenue;
    }
    public LinkedList<Store> getStores(){
        return stores;
    }
    public int totalSold(){
        int size = 0;
        for(Store x: stores){
            size += x.totalSold();
        }
        return size;
    }
    public Store findStore(String storename){
        Store found = null;
        for(Store x: stores){
            if(x.getStoreName().equals(storename)){
                found =   x;
                Candle cn = new Candle(3,3,"sad");
                 int sad = cn.getPrice();
                 System.out.println(sad);
            }

        }
        return found;

    }




}
