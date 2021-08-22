package FrontEnd;

import BackEnd.DatabaseMethods;
import BackEnd.ReadFromCsv;
import Statistics.Distributor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.*;

public final class Distribution extends javax.swing.JFrame {
    private static DatabaseMethods method;
    private static Connection connection;
    private static DefaultTableModel model = new DefaultTableModel();
    private static DefaultTableModel distributionmodel = new DefaultTableModel();
    private static String [] selectquery = new String[6];
    private static String Data[];
    private static String filename2;
    private static Distributor m = new Distributor();
    boolean pass = true;
    ReadFromCsv readcsv = new ReadFromCsv();





    public Connection connect() {
    // Creates a connection
    Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void distributionTable(){
        //inserts data from the databasetable Distributions into the jTable that shows the distributionlog
        DatabaseMethods mts = new DatabaseMethods();
        distributionmodel.setRowCount(0);
        distributionmodel.setColumnCount(0);

        distributionmodel.addColumn("Code");
        distributionmodel.addColumn("Store");
        distributionmodel.addColumn("Quantity");
        distributionmodel.addColumn("Price");


        try{
            Connection econnection = this.connect();
            Statement estatement = econnection.createStatement();
            mts.distributiontables(0, distributionmodel, estatement, distributiontable, statisticstore);
        }catch(Exception e ){ e.printStackTrace();   }




    }

    public void distributionTable2(){
        //inserts data from the databasetable Stock into the jTable that shows the current Stock
        model.setRowCount(0);
        model.setColumnCount(0);
        method = new DatabaseMethods();

        model.addColumn("Code");
        model.addColumn("Format");
        model.addColumn("AlphaCode");
        model.addColumn("Quantity");
        model.addColumn("Price");
        Data = new String[5];
        try{
            Connection connection2 = this.connect();
            Statement declaration = connection2.createStatement();
            DatabaseMethods mts = new DatabaseMethods();
            mts.distributiontables(1, model,declaration,  Table, statisticstore);
        }catch(Exception e ){e.printStackTrace();}
    }

    public void distributionTable3(){
        //shows the data from the databaseTable Distributions but only shows the distributions from each store
        try{ Connection connection3 = this.connect();
            Statement sta = connection3.createStatement();
            DefaultTableModel model3 = new DefaultTableModel();
            model3.setRowCount(0);
            model3.setColumnCount(0);
            model3.addColumn("Code");
            model3.addColumn("Quantity");
            model3.addColumn("Price");
            DatabaseMethods sa = new DatabaseMethods();
            sa.distributiontables(2, model3, sta,table3, statisticstore);
        }catch (Exception s){s.printStackTrace();}

    }

    public void dashboard(){
        //adds welcome user to dashboard
        Loginapp lapp = new Loginapp();
        usergreet.setText("Welcome " + lapp.getUser());
        m = new Distributor();
        try{
            m.storeList();

            for (int x =0; x<m.getStores().size(); x++) {
                m.getStores().get(x).candlesFromDB(m.getStores().get(x), m.getStores().get(x).getStoreName());
            }


            outopselling.setText(m.getBestSellingCandle().getCode());
            outopselling2.setText(Integer.toString(m.getBestSellingCandle().getQuantity()));
            outleastselling.setText(m.getWorstSellingCandle().getCode());
            outleastselling2.setText(Integer.toString(m.getWorstSellingCandle().getQuantity()));



            //calculates revenue, amount sold, value of stock and netprofit
            outrevenue.setText(m.getBestSellingStore().getStoreName());
            outstockprice.setText(m.getworstSellingStore().getStoreName());
            outnetprofit.setText(Integer.toString(m.getRevenue()));
            while (pass) {
                pass = false;
                outotalsold.setText(Integer.toString(m.totalSold()));
                // Displays the most delivered product and the least delivered product on the dashboard using a binary tree
            }

        }catch(Exception ex){}

    }

    public  Distribution() {
        //initializes earlier methods and components
        initComponents();
        distributionTable();
        distributionTable2();
        dashboard();
    }


    private void initComponents() {
        //sets all java swing variables
        jLabel37 = new javax.swing.JLabel(new ImageIcon(getClass().getResource("image.png")));
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        tabbed = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        stockButton = new javax.swing.JButton();
        distributionButton = new javax.swing.JButton();
        statisticsbutton = new javax.swing.JButton();
        logoutbutton4 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        outleastselling = new javax.swing.JLabel();
        outopselling = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        outleastselling2 = new javax.swing.JLabel();
        outopselling2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        outrevenue = new javax.swing.JLabel();
        outstockprice = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        outnetprofit = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        outotalsold = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        usergreet = new javax.swing.JLabel();
        logoutbutton5 = new javax.swing.JButton();
        refreshStatistics2 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        StockPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        stockCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        selecttheItems = new javax.swing.JButton();
        selectoutput = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        outcode = new javax.swing.JLabel();
        outalphacode = new javax.swing.JLabel();
        outformat = new javax.swing.JLabel();
        outaroma = new javax.swing.JLabel();
        outprice = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        stockquantity = new javax.swing.JTextField();
        addshipment = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        loadfileButton = new javax.swing.JButton();
        CSVOUT = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        logoutbutton3 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        distributioncode = new javax.swing.JTextField();
        distributionquantity = new javax.swing.JTextField();
        storepicker = new javax.swing.JComboBox<>();
        transferbutton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        distributionprice = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        storepicker1 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        importFileButton = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        transferButton = new javax.swing.JButton();
        OUTCSV = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        distributiontable = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        logoutbutton2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        statisticstore = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        refreshstatistics = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        outstore = new javax.swing.JLabel();
        outpurchased = new javax.swing.JLabel();
        outproducts = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        logoutbutton = new javax.swing.JButton();
        storelabelname = new javax.swing.JLabel();





        storelabelname.setFont(new java.awt.Font("Segoe UI", 0, 15));
        storelabelname.setText("SAD");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        jLabel39.setText("   ");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 23)); // NOI18N
        jLabel40.setText("Consider Purchasing:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Distribution");
        setBackground(new java.awt.Color(255, 255, 255));

        tabbed.setBackground(new java.awt.Color(255, 255, 255));
        selecttheItems.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Dashboard");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setText("Quick Menu ");

        stockButton.setText("Stock");
        stockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockButtonActionPerformed(evt);
            }
        });

        distributionButton.setText("Distribution");
        distributionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distributionButtonActionPerformed(evt);
            }
        });

        statisticsbutton.setText("Statistics");
        statisticsbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsbuttonActionPerformed(evt);
            }
        });

        logoutbutton4.setText("Logout");
        logoutbutton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbutton4ActionPerformed(evt);
            }
        });
        //adds components to jPanel12
        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(stockButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(distributionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(statisticsbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(logoutbutton4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18)
                                .addComponent(stockButton)
                                .addGap(16, 16, 16)
                                .addComponent(distributionButton)
                                .addGap(18, 18, 18)
                                .addComponent(statisticsbutton)
                                .addGap(23, 23, 23)
                                .addComponent(logoutbutton4)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel30.setText("Top Selling Product:");

        outleastselling.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        outleastselling.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outleastselling.setText(" ");

        outopselling.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        outopselling.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outopselling.setText(" ");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setText("Consider Purchasing:");

        outleastselling2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        outleastselling2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outleastselling2.setText(" ");

        outopselling2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        outopselling2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outopselling2.setText(" ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel30)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel36)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(outopselling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(outleastselling, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(outleastselling2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(outopselling2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap())))
        );
        jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(7, 7, 7)
                                .addComponent(outopselling)
                                .addGap(11, 11, 11)
                                .addComponent(outopselling2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outleastselling)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outleastselling2))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel31.setIcon(new ImageIcon(getClass().getResource("image.png")));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(jLabel31)
                                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel31)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setText("Best Selling Store: ");

        outrevenue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        outrevenue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outrevenue.setText(" ");

        outstockprice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        outstockprice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outstockprice.setText(" ");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setText("Least Selling Store: ");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(jLabel35))
                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(outrevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel32)
                                                        .addComponent(outstockprice, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outrevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outstockprice, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText("Revenue from Distribution");

        outnetprofit.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        outnetprofit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outnetprofit.setText(" ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(jLabel33))
                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addGap(79, 79, 79)
                                                .addComponent(outnetprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(outnetprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setText("Total Products Sold");

        outotalsold.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        outotalsold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outotalsold.setText(" ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addComponent(jLabel34))
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addGap(72, 72, 72)
                                                .addComponent(outotalsold, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(outotalsold, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        usergreet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        logoutbutton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        logoutbutton5.setText("Logout");
        logoutbutton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbutton5ActionPerformed(evt);
            }
        });

        refreshStatistics2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        refreshStatistics2.setText("Refresh Statistics");
        refreshStatistics2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshStatistics2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(usergreet)
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(logoutbutton5))
                                        .addComponent(refreshStatistics2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(usergreet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(refreshStatistics2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoutbutton5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(177, 204, 248));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 22, Short.MAX_VALUE)
        );

        jPanel22.setBackground(new java.awt.Color(177, 204, 248));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 947, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 21, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(29, 29, 29)
                                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(150, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel23)
                                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(68, Short.MAX_VALUE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(607, Short.MAX_VALUE)))
        );

        tabbed.addTab("Dashboard", jPanel11);

        StockPanel.setBackground(new java.awt.Color(255, 255, 255));

        Table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Code", "Store", "Quantity", "Price"
                }
        ));
        jScrollPane1.setViewportView(Table);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Add Shipment");

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        stockCode.setText("V-");


        jLabel3.setText("Add Product Code:");

        selecttheItems.setText("Select Item");
        selecttheItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecttheItemsActionPerformed(evt);
            }
        });

        jLabel4.setText("Code:");

        jLabel7.setText("AlphaCode:");

        jLabel8.setText("Format: ");

        jLabel9.setText("Aroma: ");

        jLabel10.setText("Price:");


        jLabel6.setText("Select Quantity:");


        addshipment.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        addshipment.setText("LOAD SHIPMENT TO STOCK");
        addshipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addshipmentActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Add Shipment Manually");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addComponent(selectoutput, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                .addGap(15, 15, 15))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(selecttheItems, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(stockCode, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(stockquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel8)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(74, 74, 74)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(outformat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(outcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(outalphacode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(outprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(outaroma, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(addshipment, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(106, 106, 106))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(outcode))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(outalphacode))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel8))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(outformat)))
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(outaroma)
                                                        .addComponent(jLabel9))
                                                .addGap(9, 9, 9)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel6)
                                                                .addComponent(stockquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel10)
                                                        .addComponent(outprice)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(stockCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(selecttheItems)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addshipment, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(selectoutput))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Load Inventory");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Load from Spreadsheet");

        loadfileButton.setText("LoadFile");
        loadfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadfileButtonActionPerformed(evt);
            }
        });

        CSVOUT.setText("Status:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(CSVOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel19)
                                                                .addGap(0, 11, Short.MAX_VALUE))
                                                        .addComponent(loadfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(loadfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CSVOUT)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGap(0, 274, Short.MAX_VALUE)))
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(118, 118, 118))))
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(52, Short.MAX_VALUE))
        );

        jLabel11.setText("You can only enter one shipment per Session");

        logoutbutton3.setText("Refresh");
        logoutbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbutton3ActionPerformed(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(177, 204, 248));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 60)); // NOI18N
        jLabel1.setText("Stock");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout StockPanelLayout = new javax.swing.GroupLayout(StockPanel);
        StockPanel.setLayout(StockPanelLayout);
        StockPanelLayout.setHorizontalGroup(
                StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(StockPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(StockPanelLayout.createSequentialGroup()
                                                .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(StockPanelLayout.createSequentialGroup()
                                                                .addGap(4, 4, 4)
                                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(StockPanelLayout.createSequentialGroup()
                                                                .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jLabel11))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(logoutbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        StockPanelLayout.setVerticalGroup(
                StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(StockPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(StockPanelLayout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(StockPanelLayout.createSequentialGroup()
                                                .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(StockPanelLayout.createSequentialGroup()
                                                                .addGap(43, 43, 43)
                                                                .addComponent(logoutbutton3))
                                                        .addGroup(StockPanelLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel11)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(21, 21, 21))
        );

        tabbed.addTab("Stock", StockPanel);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Stock Item Code: ");
        jLabel14.setText("Quantity: ");

        jLabel16.setText("Store: ");

        distributioncode.setText("V-");


        storepicker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[SELECT]", "SUEÑOS DE OPIOBE HOUSE", "BIOMBO", "CLAIRE DE LUNE", "HAPPY GIFTS", "PHARMAX EL POLO", "PHARMAX SALAVERRY", "SUEÑOS DE OPIO" ,"VIVERO LA MOLINA", "VIVERO SAN ISIDRO", "OTHER" }));


        transferbutton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        transferbutton2.setText("TRANSFER");
        transferbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferbutton2ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Stock to Store");

        jLabel15.setText("Price");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(distributionquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel16)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(storepicker, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel13)
                                                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(distributionprice, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(distributioncode, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addComponent(jLabel17)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(transferbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(distributioncode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14)
                                        .addComponent(distributionquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(distributionprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(storepicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16))
                                .addComponent(transferbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        storepicker1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[SELECT]", "SUEÑOS DE OPIOBE HOUSE", "BIOMBO", "CLAIRE DE LUNE", "HAPPY GIFTS", "PHARMAX EL POLO", "PHARMAX SALAVERRY", "SUEÑOS DE OPIO", "VIVERO LA MOLINA", "VIVERO SAN ISIDRO", "OTHER" }));


        jLabel20.setText("Store: ");

        importFileButton.setText("Import File");
        importFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importFileButtonActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Import From Spreadsheet");

        transferButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        transferButton.setText("TRANSFER");
        transferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferButtonActionPerformed(evt);
            }
        });

        OUTCSV.setText("Transfer Status");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel21)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(importFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(jLabel20)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(storepicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(transferButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                                        .addComponent(OUTCSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel21)
                                .addGap(15, 15, 15)
                                .addComponent(importFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel20)
                                        .addComponent(storepicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(transferButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(OUTCSV)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        distributiontable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Code", "Store", "Quantity", "Price"
                }
        ));
        jScrollPane2.setViewportView(distributiontable);

        jTabbedPane2.addTab("Distribution Log", jScrollPane2);

        jPanel24.setBackground(new java.awt.Color(177, 204, 248));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        jLabel12.setText("Distribution");

        logoutbutton2.setText("Logout");
        logoutbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbutton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logoutbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logoutbutton2))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(31, 31, 31)
                                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                                                .addContainerGap())))
        );

        tabbed.addTab("Distribution ", jPanel2);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        table3.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "Code", "Quantity", "Price"
                }
        ));
        jScrollPane3.setViewportView(table3);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        statisticstore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[SELECT]", "SUEÑOS DE OPIOBE HOUSE", "BIOMBO", "CLAIRE DE LUNE", "HAPPY GIFTS", "PHARMAX EL POLO", "PHARMAX SALAVERRY", "SUEÑOS DE OPIO", "SW", "VIVERO LA MOLINA", "VIVERO SAN ISIDRO", "OTHER" }));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Select Store");

        refreshstatistics.setText("Refresh Statistics");
        refreshstatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshstatisticsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(153, 153, 153)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(statisticstore, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(refreshstatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(170, 170, 170)
                                                .addComponent(jLabel24)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel24)
                                .addGap(19, 19, 19)
                                .addComponent(statisticstore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(refreshstatistics)
                                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Store Revenue:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Best Selling Product: ");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel28.setText("Store: ");

        outstore.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        outstore.setText(" ");

        outpurchased.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        outpurchased.setText(" ");

        outproducts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        outproducts.setText(" ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addComponent(jLabel28)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(outstore, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addComponent(jLabel25)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(outpurchased, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGap(45, 45, 45)

                                                .addComponent(jLabel27)
                                                .addComponent(outproducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel28)
                                        .addComponent(outstore))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel25)
                                        .addComponent(outpurchased))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel27)
                                        .addComponent(outproducts))
                                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(177, 204, 248));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        jLabel22.setText("Statistics");

        logoutbutton.setText("Logout ");
        logoutbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbutton(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logoutbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel22)
                                        .addComponent(logoutbutton)))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(42, 42, 42))))
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 245, Short.MAX_VALUE))
                                        .addComponent(jScrollPane3))
                                .addContainerGap())
        );

        tabbed.addTab("Statistics", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabbed)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabbed)
        );
        pack();
    }

    //Action Listeners from buttons in Distributions

    //transfer button uses readandinsert2 from ReadFromCSV to read the csv file and insert to the selected store
    //all the items in the csv files
    private void transferButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if(!storepicker1.getSelectedItem().toString().equals("[SELECT]")){
        OUTCSV.setText("Loading...");

        readcsv.readandinsert2(filename2,storepicker1.getSelectedItem().toString());
        JOptionPane.showMessageDialog(StockPanel, "Loading Shipment, Please Wait");
        OUTCSV.setText("Loaded");}
        else{JOptionPane.showMessageDialog(StockPanel, "Please enter a Store"); }
        importFileButton.setForeground(Color.black);
        distributionTable();

    }

    //import file uses class Filechooser to select a file from the computer and save the directory
    private void importFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser2 = new JFileChooser();
        chooser2.showOpenDialog(null);
        File f = chooser2.getSelectedFile();
        filename2 = f.getAbsolutePath();
        importFileButton.setForeground(Color.red);
    }

    private void transferbutton2ActionPerformed(java.awt.event.ActionEvent evt) {


        if(storepicker.getSelectedItem().equals("[SELECT]")){
            JOptionPane.showMessageDialog(StockPanel, "Please Enter a Store");
        }else{
        if (!distributioncode.getText().equals("V-") || !distributionquantity.getText().isEmpty() || !distributionprice.getText().equals("")) {
            try {
                String convert = distributionquantity.getText();
                int converted = Integer.parseInt(convert);
                //Creates 3 Connections to the database
                connection = this.connect();
                Connection connection2 = this.connect();
                Connection connection3 = this.connect();
                Connection connection4 = this.connect();
                Statement statement6 = connection4.createStatement();
                Statement statement5 = connection3.createStatement();
                Statement statement4 = connection2.createStatement();
                //Creates 3 statements that are going to be used
                DatabaseMethods mts = new DatabaseMethods();
                //Calls method transfer candles from database
                mts.transferCandles(statement6, statement4, statement5, distributioncode, storepicker, converted, distributionprice);


            } catch (Exception ex) {ex.printStackTrace();}
            distributioncode.setText("V-");
            distributionquantity.setText("");
            distributionprice.setText("");
            JOptionPane.showMessageDialog(StockPanel, "Shipment Added");
            distributionTable();
        }else{JOptionPane.showMessageDialog(StockPanel, "Data entered cannot be empty"); }}
    }


    //When the button is pressed the csv file that was selected gets read by method .readfromcsv
    private void loadfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //when button is pressed and a loading prompt is outputted to JLabel CSVOUT
        CSVOUT.setText("Loading...");
        //a new instance of JFileChooser from library java swing called chooser is created
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        // a new instance of File from library java.io called "file" is created
        File file = chooser.getSelectedFile();
        // filepath contains the file path; File path is obtained using method getabsolute path form java.io
        String filepath = file.getAbsolutePath();

        //method read and insert, from class ReadFromCsv is called
        readcsv.readandinsert(filepath);

        // a java alert is prompted saying
        JOptionPane.showMessageDialog(StockPanel, "Shipment has started, Please Wait");

        //JTable is refreshed and loaded is outputted to jlabel
        distributionTable2();
        CSVOUT.setText("Loaded");

    }


    //add shipment adds the code enter to the stock
    private void addshipmentActionPerformed(java.awt.event.ActionEvent evt) {

        try{
            System.out.println(selectquery[0]);
            connection = this.connect();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            Statement statement4 = connection.createStatement();

            DatabaseMethods mts = new DatabaseMethods();
            mts.addShimpent(statement1, statement2,statement3,statement4, selectquery, stockquantity);
        }catch(Exception ex){ex.printStackTrace();}

        JOptionPane.showMessageDialog(StockPanel, "Shipment Added");
        stockCode.setText("V-");
        stockquantity.setText("");
        distributionTable2();

    }
    //select the items gets value from a candle with the specifcations from stock
    private void selecttheItemsActionPerformed(java.awt.event.ActionEvent evt) {
        if(!selectquery.toString().equals("V-")|| !selectquery.toString().equals("V-")){
            if(!stockquantity.toString().isEmpty()){
        String sqlquery = "SELECT Code, AlphaCode, Format, Aroma, Price, Aroma FROM Products WHERE Code = '" + stockCode.getText() + "';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sqlquery)){
            method.selectItems(selectquery, rs, outcode, outalphacode, outformat, outaroma, outprice);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }}else{JOptionPane.showMessageDialog(StockPanel, "Please enter Quantity"); }}else{JOptionPane.showMessageDialog(StockPanel, "Please select an item");}


    }
    //refresh statistics reloads statistics from Statistics tab
    private void refreshstatisticsActionPerformed(java.awt.event.ActionEvent evt) {

        distributionTable3();
        outstore.setText(statisticstore.getSelectedItem().toString());
        outproducts.setText(m.findStore(statisticstore.getSelectedItem().toString()).storebBestSellingCandle().getCode());
        outpurchased.setText("$" + m.findStore(statisticstore.getSelectedItem().toString()).storeRevenue());
    }
    //refresh statistics2 reloads statistics shown in dashboard
    private void refreshStatistics2ActionPerformed(java.awt.event.ActionEvent evt) {
        dashboard();

    }
    //The following buttons are part of the Menu in the dashboard that act as shorcuts; They change the current tab to the selected tab

    private void stockButtonActionPerformed(java.awt.event.ActionEvent evt) {
        tabbed.setSelectedIndex(1);

    }

    private void distributionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        tabbed.setSelectedIndex(2);
    }

    private void statisticsbuttonActionPerformed(java.awt.event.ActionEvent evt) {
        tabbed.setSelectedIndex(1);
    }

    //All logout buttons closes Distributions and opens Loginapp

    private void logoutbutton(java.awt.event.ActionEvent evt) {
        new Loginapp().setVisible(true);
        dispose();
    }
    
    private void logoutbutton2ActionPerformed(java.awt.event.ActionEvent evt) {
        new Loginapp().setVisible(true);
        dispose();
    }

    private void logoutbutton3ActionPerformed(java.awt.event.ActionEvent evt) {
        distributionTable2();
    }

    private void logoutbutton4ActionPerformed(java.awt.event.ActionEvent evt) {
        new Loginapp().setVisible(true);
        dispose();
    }
    
    private void logoutbutton5ActionPerformed(java.awt.event.ActionEvent evt) {
        new Loginapp().setVisible(true);
        dispose();
    }


    //main class selects the look of Java Swing
    public static void main(String args[]) {
        //selects the look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Distribution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Distribution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Distribution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Distribution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Distribution().setVisible(true);
            }
        });
    }

    //Declared variables from JAVA SWING
    private javax.swing.JLabel CSVOUT;
    private javax.swing.JLabel OUTCSV;
    private javax.swing.JPanel StockPanel;
    private javax.swing.JTable Table;
    private javax.swing.JButton addshipment;
    private javax.swing.JTextField distributioncode;
    private javax.swing.JTextField distributionquantity;
    private javax.swing.JTextField distributionprice;
    private javax.swing.JTable distributiontable;
    private javax.swing.JButton selecttheItems;
    private javax.swing.JButton logoutbutton;
    private javax.swing.JButton stockButton;
    private javax.swing.JButton distributionButton;
    private javax.swing.JButton statisticsbutton;
    private javax.swing.JButton logoutbutton4;
    private javax.swing.JButton refreshStatistics2;
    private javax.swing.JButton logoutbutton5;
    private javax.swing.JButton transferbutton2;
    private javax.swing.JButton transferButton;
    private javax.swing.JButton logoutbutton2;
    private javax.swing.JButton importFileButton;
    private javax.swing.JButton loadfileButton;
    private javax.swing.JButton logoutbutton3;
    private javax.swing.JButton refreshstatistics;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel storelabelname;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel outalphacode;
    private javax.swing.JLabel outaroma;
    private javax.swing.JLabel outcode;
    private javax.swing.JLabel outformat;
    private javax.swing.JLabel outleastselling;
    private javax.swing.JLabel outleastselling2;
    private javax.swing.JLabel outnetprofit;
    private javax.swing.JLabel outopselling;
    private javax.swing.JLabel outopselling2;
    private javax.swing.JLabel outotalsold;
    private javax.swing.JLabel outprice;
    private javax.swing.JLabel outproducts;
    private javax.swing.JLabel outpurchased;
    private javax.swing.JLabel outrevenue;
    private javax.swing.JLabel outstockprice;
    private javax.swing.JLabel outstore;
    private javax.swing.JLabel selectoutput;
    private javax.swing.JComboBox<String> statisticstore;
    private javax.swing.JTextField stockCode;
    private javax.swing.JTextField stockquantity;
    private javax.swing.JComboBox<String> storepicker;
    private javax.swing.JComboBox<String> storepicker1;
    private javax.swing.JTabbedPane tabbed;
    private javax.swing.JTable table3;
    private javax.swing.JLabel usergreet;
}
