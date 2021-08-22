package BackEnd;


import java.io.File;
import java.sql.*;
import java.util.Scanner;


public class ReadFromCsv {
    //Creates connection with database
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    //reads and insert data from a csv
    public void readandinsert(String fileName){
        //A new instance of file is created
        File file = new File(fileName);
        try{
            //new instance of scanner is created with its input being the selected file entered by the user
            Scanner filescanner = new Scanner(file);
            //scanner goes through each comma of the CSV file
            while(filescanner.hasNext()){
                //A string called data is now equal to the first row
                String data = filescanner.next();
                //the first line is always ITEM QUANTITY AND PRICE so that the user knows what to enter in the csv file
                if(!"Item,Quantity,Price".equals(data))
                {
                    //An array is created and it contains each of the values between the commas
                    String values[] = data.split(",");
                    //connection with the database is established
                    Connection connection = this.connect();
                    Statement statement = connection.createStatement();
                    //Looks to get data from a candle in the database table Products
                    String query = "SELECT AlphaCode, Format, Price FROM Products WHERE Code = 'V-" + values[0] + "';";
                    ResultSet rs = statement.executeQuery(query);
                    String[] results =  new String[4];

                    while(rs.next()){
                        results[0] = rs.getString("AlphaCode");
                        results[1] = rs.getString("Format");
                        results[2] = rs.getString("Price");
                    }
                    //Query that gets the Quantity and Code from the database stock to see if the value exists
                    String query2 = "SELECT Quantity, Code FROM Stock WHERE Code = 'V-" + values[0] + "';";
                    System.out.println(query2);
                    Statement state2 = connection.createStatement();
                    ResultSet rs2 = state2.executeQuery(query2);
                    String querycode = "";
                    int queryint = -1;
                    while(rs2.next()){
                        queryint = rs2.getInt("Quantity");
                        querycode = rs2.getString("Code");
                    }
                    System.out.println("Code: " + values[0] + "| Alpha: " + results[0] + "| Format: " + results[1]+ "| Price: " + results[2] + "| Quantity: " + values[1] +"| Real Price: " + values[2]);

                    //updates the Distributions table if the product has previously been transfered or adds it if its never been transfered before
                    try{
                        if(querycode.equals("V-" + values[0])){
                            String sat = "SELECT Quantity From Stock Where Code= '" + values[0] + "';";
                            System.out.println(sat);
                            int intquantity = Integer.parseInt(values[1]);
                            int additionquantity = intquantity + queryint;
                            String intostock = "UPDATE Stock SET Quantity = '" + additionquantity + "' WHERE Code = 'V-" + values[0] + "';";
                            System.out.println(intostock);
                            statement.executeQuery(intostock);

                        }
                        else{
                            String intostock = "INSERT INTO Stock(Code,Format,AlphaCode,Quantity,Price) VALUES('V-" + values[0] +"', '" + results[1] +"', '" + results[0] + "', '" + values[1] + "', '" + results[2] + "');";
                            System.out.println(intostock);
                            statement.executeQuery(intostock);
                        }

                    }catch(Exception ex){}
                }else{}
            }
            filescanner.close();
        }catch(Exception e){}


    }
    public void readandinsert2(String fileName, String store){
        //A new instance of file is created

        System.out.println("Button pressed");
        File file = new File(fileName);
        try{
            //new instance of scanner is created with its input being the selected file entered by the user
            Scanner filescanner = new Scanner(file);
            //scanner goes through each row of the CSV file
            while(filescanner.hasNext()){
                //A string called data is now equal to the first row
                String data = filescanner.next();
                System.out.println(data);
                additems(data,store);
            }
            filescanner.close();
        }catch(Exception e){}



    }
    public void additems(String data, String store){
        try{
        //the first line is always ITEM QUANTITY AND PRICE so that the user knows what to enter in the csv file
        if(!"Code,Quantity,Price".equals(data)){
            //An array is created and it contains each of the values between the commas
            String values[] = data.split(",");
            //connection with the database is established
            Connection connection = this.connect();
            Statement statement = connection.createStatement();
            //Looks to get data from a candle in the database table Products
            String querycode = "";
            String thestore = "";
            try {
                //Query that gets the Quantity and Code from the database stock to see if the value exists
                String query2 = "SELECT Store, Code FROM Distributions WHERE Code = 'V-" + values[0] + "' AND Store = '" + store +"';";
                System.out.println(query2);
                Statement state2 = connection.createStatement();
                ResultSet rs2 = state2.executeQuery(query2);
                while (rs2.next()) {
                    querycode = rs2.getString("Code");
                    thestore = rs2.getString("Store");

                }
                System.out.println("The store:" + thestore);
                System.out.println("The Code: " + querycode);
            }catch (Exception e) {e.printStackTrace();}
            //updates the Distributions table if the product has previously been transfered or adds it if its never been transfered before
            try{
                if(!querycode.equals("")&&!thestore.equals("")){
                    String intostock = "UPDATE Distributions SET Quantity = Quantity + '" + values[1] + "' WHERE Code = 'V-" + values[0] + "' AND  Store = '"+ store +"';";
                    System.out.println(intostock);
                    statement.executeQuery(intostock);

                }
                else{
                    String intostock = "INSERT INTO Distributions(Code,Store,Quantity,Price) VALUES('V-" + values[0] +"', '" + store + "', '"+ values[1] + "', '" + values[2] + "');";
                    System.out.println(intostock);
                    statement.executeQuery(intostock);
                }


            String query = "UPDATE Stock SET Quantity= Quantity - '" + values[1] + "' WHERE Code = 'V-" + values[0] + "';";
            statement.executeQuery(query);
            }catch(Exception ex){}
        }
        }catch (Exception e){}
    }
}

