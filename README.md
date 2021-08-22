# stack
A program used to keep track of inventory and distributions in a candle store

This was my first ever JAVA project. I developed it for a client who needed to keep track of the amounts of candles she had on stock, and the amount of candles she had distributed to different stores. 

Before logging in you can choose between USER and ADMIN.

ADMIN:
Can add and remove Candles from the database. These candles represent the available models that can be added to the store.

USER:
The user can add candles to the stock, distribute candles to a store (which removes the candles from stock), and view general statistics about different candles and stores. The program can also read in from CSV files, this was a request from the customer who often received the data as spreadsheets. In order to read a csv file, it must have as columns either Item, Quantity and Price.

IMPORTANT:
You need to download sqlite-jdbc jar file and then add it as a dependency in your IDE. If not, the program will be unable to connect to the database.
Loginapp.java inside of frontend is the main class

