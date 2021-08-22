package BackEnd;

import FrontEnd.Admin;
import FrontEnd.Distribution;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseMethods {

    public void addItems(javax.swing.JTextField textcode,   javax.swing.JTextField textalphacode,javax.swing.JTextField textformat, javax.swing.JTextField textaroma,javax.swing.JTextField textprice,  Statement statement) {
        //Changes JTextField ,where the user has entered the data of the product they want, to add to a string
        String code = "'" + textcode.getText() + "', " ;
        String alphacode = "'" + textalphacode.getText()+ "', ";
        String format = "'" + textformat.getText() + "', ";
        String aroma = "'" + textaroma.getText()+ "', " ;
        String price = "'"+textprice.getText()+"'";
        // Prepare the sqlQuery that is going to INSERT all the data entered by the user to the database Products
        String sqlquery = "INSERT INTO Products (Code, AlphaCode, Format, Aroma, Price) VALUES " + "(" + code + alphacode + format +aroma + price + ");";
        System.out.println(sqlquery);

        try{
            statement.executeQuery(sqlquery);
            statement.close();
        }
        catch(Exception ex){}

    }
    public void removeItems(javax.swing.JTextField textcode, javax.swing.JTextField textalphacode,javax.swing.JTextField textformat,javax.swing.JTextField textaroma, javax.swing.JTextField  textprice, Statement statement){
        //Changes JTextField ,where the user has entered the data of the product they want, to add to a string
        String code =      " = '" + textcode.getText() + "' AND " ;
        String alphacode = " = '" + textalphacode.getText()+ "' AND ";
        String format =    " = '" + textformat.getText() + "' AND ";
        String aroma =     " = '" + textaroma.getText()+ "' AND " ;
        String price =     " = '"+textprice.getText()+"';";
        // Prepare the sqlQuery that is going to DELETE the product using the code the data entered by the user to the database Products
        String sqlquery = "DELETE FROM Products WHERE Code" + code + "AlphaCode" + alphacode + "Format" + format + "Aroma" + aroma +"Price" + price;
        System.out.println(sqlquery);
        try{
            statement.executeQuery(sqlquery);
            statement.close();

        }catch(Exception ex){}
    }
    public void refreshTable(int rtable, Statement statement,DefaultTableModel model, javax.swing.JTable table, String databasetable ){
        model.setRowCount(0);
        String Dato[];
        if (rtable == 1){
            Dato = new String[5];
            try{
                String query = "SELECT "
                        + "Code, "
                        + "AlphaCode, "
                        + "Format, "
                        + "Aroma, "
                        + "Price "
                        + "FROM " + databasetable + ";";
                System.out.println(query);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    Dato[0]=rs.getString("Code");
                    Dato[1]=rs.getString("AlphaCode");
                    Dato[2]=rs.getString("Format");
                    Dato[3]=rs.getString("Aroma");
                    Dato[4]=rs.getString("Price");
                    model.addRow(Dato);
                }
                table.setModel(model);

            } catch (SQLException e) {e.printStackTrace();}
        }
        else if (rtable ==2){
            try{
                Dato = new String[5];
                String query = "SELECT "
                        + "Code, "
                        + "Format, "
                        + "AlphaCode, "
                        + "Quantity, "
                        + "Price "
                        + "FROM " + databasetable + ";";
                System.out.println(query);

                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    Dato[0]=rs.getString("Code");
                    Dato[1]=rs.getString("Aroma");
                    Dato[2]=rs.getString("AlphaCode");
                    Dato[3]=rs.getString("Quantity");
                    Dato[84]=rs.getString("Price");

                    model.addRow(Dato);
                }
                table.setModel(model);

            } catch (SQLException e) {}}
        else if(rtable== 3){
            model.setRowCount(0);
            model.setColumnCount(0);
            model.addColumn("Code");
            model.addColumn("Store");
            model.addColumn("Quantity");
            model.addColumn("Price");

            Dato = new String[4];
            try{
                String query = "SELECT "
                        + "Code,"
                        + "Store, "
                        + "Quantity, "
                        + "Price "
                        + "FROM Distributions;";
                System.out.println(query);
                ResultSet rs2 = statement.executeQuery(query);
                while (rs2.next()) {
                    Dato[0]=rs2.getString("Code");
                    Dato[1]=rs2.getString("Store");
                    Dato[2]=rs2.getString("Quantity");
                    Dato[3]=rs2.getString("Price");
                    model.addRow(Dato);
                }
                table.setModel(model);
            }catch(Exception ex){ex.printStackTrace();}finally {
                try{
                    statement.close();

                }
                catch(Exception e) {
                }}
        }
        //takes in different data from textfields and then modifies the text so that it can be read by SQLITE and then refreshs the information in the table
    }
    public void selectItems(String[] selectquery, ResultSet rs, javax.swing.JLabel outcode, javax.swing.JLabel outalphacode, javax.swing.JLabel outformat, javax.swing.JLabel outaroma , javax.swing.JLabel outprice){
        try{
            while (rs.next()) {
                selectquery[0] = rs.getString("Code");
                selectquery[1] = rs.getString("AlphaCode");
                selectquery[2] = rs.getString("Format");
                selectquery[3] = rs.getString("Aroma");
                selectquery[4] = rs.getString("Price");
            }
            outcode.setText(selectquery[0]);
            outalphacode.setText(selectquery[1]);
            outformat.setText(selectquery[2]);
            outaroma.setText(selectquery[3]);
            outprice.setText(selectquery[4]);
        }catch(Exception ex ){}
        //selects items and outputs the  information to show the data of each product
    }
    public void distributiontables(int type, DefaultTableModel Model,Statement statement, javax.swing.JTable table,JComboBox storechooser){
        if (type == 0) {
            //Creates an array that is going to be filled with the code, store, quantity and price of each candle
            String [] Data = new String[5];
            try{
                String query = "SELECT Code, Store, Quantity, Price FROM Distributions;";
                //Query that returns Code, store quantity and price from all of the candles in distributions
                ResultSet rs2 = statement.executeQuery(query);
                //rs2 contains the values returned by the query
                while (rs2.next()) {
                    //for every row in the database, array Data is populated with the code,store quantity and price
                    Data[0] = rs2.getString("Code");
                    Data[1] = rs2.getString("Store");
                    Data[2] = rs2.getString("Quantity");
                    Data[3] = rs2.getString("Price");
                    // use the .addrow to add a row in the default table model where each column is a value of the array
                    Model.addRow(Data);
                }
                //once the while loop has gone through all of the values in the results set rs2 the Jtable is asigned the table model
                table.setModel(Model);


            }catch(SQLException e ){e.printStackTrace();}


        }
        if(type == 1){
            String [] Data = new String[5];
            try{
                String query2 = "SELECT "
                        + "Code, "
                        + "Format, "
                        + "AlphaCode, "
                        + "Quantity, "
                        + "Price "
                        + "FROM Stock;";
                System.out.println(query2);
                ResultSet rs2 = statement.executeQuery(query2);
                while (rs2.next()) {
                    Data[0]=rs2.getString("Code");
                    Data[1]=rs2.getString("Format");
                    Data[2]=rs2.getString("AlphaCode");
                    Data[3]=rs2.getString("Quantity");
                    Data[4]=rs2.getString("Price");
                    Model.addRow(Data);
                }
                table.setModel(Model);


            }catch(SQLException e ){e.printStackTrace();}}
        if(type == 2) {try{
            String query3 = "SELECT "
                    + "Code, "
                    + "Quantity, "
                    + "Price "
                    + "FROM Distributions WHERE Store = '" + storechooser.getSelectedItem().toString() +"';";
            System.out.println(query3);
            ResultSet rs3 = statement.executeQuery(query3);
            String[] Data3 = new String[3];
            while (rs3.next()) {
                Data3[0]=rs3.getString("Code");
                Data3[1]=rs3.getString("Quantity");
                Data3[2]=rs3.getString("Price");
                Model.addRow(Data3);
            }
            table.setModel(Model);


        }catch(Exception ex){ex.printStackTrace();}finally {
            try {
                statement.close();
            } catch (Exception e) {
            }


        }}
    }
    public boolean verifyLogin(Connection conn, JTextField loginusername, JPasswordField loginpassword, JComboBox loginstatus, JLabel logincondition){
        //create an instance of loginapp called lapp
        FrontEnd.Loginapp lapp = new FrontEnd.Loginapp();
        //a string called password holds the value of the password the user entered through JPasswordField
        //I used .getPassword to prevent the value of the password from being saved in memory (safer than using .getText)
        String password = loginpassword.getText();
        try{
            // a new instance of message diggest called MD is created and method .getInstance is ued to set the hashing algorithm to SHA 256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //use the method .update to encrypt the password entered to encrypt
            //.getBytes is ued to separate the string into an array of bytes
            md.update(password.getBytes());
            //an array of bytes called digest is now the encrypted password
            byte[] digest = md.digest();
            //convert the array of bytes to a string called my hash
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();


            //an array of string is created and it will contain the username password and permission
            String logindata[] = new String[4];
            //the query is going to return the username password and permission from the database
            String sqlquery = "SELECT Username, Password, Permission FROM Login WHERE Username = '" + loginusername.getText() + "';";

            //creating a statement with the query
            Statement stmt  = conn.createStatement();
            //executing sql query
            ResultSet rs = stmt.executeQuery(sqlquery);
            while (rs.next()) {
                //fills array with username password and permission
                logindata[0]=rs.getString("Username");
                logindata[1]=rs.getString("Password");
                logindata[2]=rs.getString("Permission");
            }
            //sets attribute username in login class
            lapp.setusername(logindata[0]);
            String user = loginusername.getText();

            //if hashed password and user are equals then the code runs
            if (user.equals(logindata[0]) && myHash.equals(logindata[1]) ){
                //if the user wants to open the "user" page then loginapp is closed and user page is opened
                if(loginstatus.getSelectedItem().toString().equals("User")){
                    new Distribution().setVisible(true);
                    return true;
                }
                //if the user is enters admin and have the corrects credentials admin page is opened and loginapp is closed
                else if(loginstatus.getSelectedItem().toString().equals("Admin")){
                    if (logindata[2].equals("Admin")){
                        new Admin().setVisible(true);
                        return true;

                    }
                    else {
                        logincondition.setText("Not enough clearance");
                    }
                }
            }else{
                //if username or password are wrong then invalid credentials are outputted on the screen
                logincondition.setText("Invalid Credentials");
                return false;
            }


        } catch (Exception e) {e.printStackTrace();}return false;
    }
    public void transferCandles(Statement statement6, Statement statement4, Statement statement5, JTextField distributioncode, JComboBox storepicker, int converted, JTextField distributionstore){
        try{
            String query = "SELECT Code, Store, Quantity FROM Distributions WHERE Code = '" + distributioncode.getText() +  "' AND Store = '" + storepicker.getSelectedItem().toString() + "';";
            System.out.println(query);

            String[] ifdata = new String[3];
            try {
                ResultSet rs3 = statement4.executeQuery(query);
                while (rs3.next()) {
                    ifdata[0] = rs3.getString("Code");
                    ifdata[1] = rs3.getString("Store");
                    ifdata[2] = rs3.getString("Quantity");
                }

            }catch (SQLException e){}
            String updateStock = "";
            String addquery = "";
            System.out.println("These are the values:" + ifdata[0] +"," + ifdata[1] + "," + ifdata[2]);
            if(distributioncode.getText().equals(ifdata[0]) && storepicker.getSelectedItem().toString().equals(ifdata[1])){


                addquery = "UPDATE Distributions SET Quantity = Quantity + '" + converted + "' WHERE Code = '" + distributioncode.getText()  + "' AND  Store = '" + storepicker.getSelectedItem().toString() + "'; ";
                updateStock = "UPDATE Stock SET Quantity= Quantity - '" + converted + "' WHERE Code = '" + distributioncode.getText() +"';";

            }
            else{
                addquery = "INSERT INTO Distributions (Store, Quantity, Code, Price) VALUES ('" + storepicker.getSelectedItem().toString() + "', '" + converted  + "', '" + distributioncode.getText() + "', '" + distributionstore.getText() + "');";
                updateStock = "UPDATE Stock SET Quantity= Quantity - '" + converted + "' WHERE Code = '" + distributioncode.getText() +"';";
            }
            queryStatement(statement5, addquery);
            queryStatement(statement6,updateStock);
        }catch(Exception ex){ex.printStackTrace();}
    }
    public void addShimpent(Statement statement1, Statement statement2, Statement statement3, Statement statement4, String[] selectquery, JTextField stockquantity){
        try{
            String lookupquery = "SELECT Code FROM Stock WHERE Code = '" + selectquery[0] + "';";
            String convert = stockquantity.getText();
            int converted = Integer.parseInt(convert);
            String thecode = "NOTFOUND";
            try{thecode = statement1.executeQuery(lookupquery).getString("Code"); System.out.println(thecode);}catch(Exception ex){System.out.println(thecode);}
            if(selectquery[0].equals(thecode)){
                int initialquantity = statement2.executeQuery("SELECT Quantity FROM Stock WHERE Code = '" + selectquery[0] + "';").getInt("Quantity");
                int additionquantity = initialquantity + converted;
                String updatequery = "UPDATE Stock SET Quantity = " + additionquantity + " where Code = '" + selectquery[0]  + "';" ;
                System.out.println(updatequery);
                statement3.executeQuery(updatequery);

            }else{
                String addquery = "INSERT INTO Stock (Code, Format, Alphacode, Quantity, Price) VALUES " + "('" + selectquery[0] + "', '" + selectquery[2] + "', '" + selectquery[1] + "', " + converted + ", " + selectquery[4] + ");";
                System.out.println(addquery);
                statement4.executeQuery(addquery);
                statement4.setQueryTimeout(1);

            }}catch(Exception ex){} finally {
                try{
                    statement1.close();
                    statement2.close();
                    statement3.close();
                    statement4.close();


                }
                catch(Exception e) {
                }
        }

    }
    public void queryStatement(Statement c, String x){
        try{
            c.executeQuery(x);
        }     catch(Exception e){}


    }

}






