

    package Statistics;

        public class Product {
            //Atributes
            private int Price;
            private int Quantity;

            //Constructor
            public Product(int price, int quantity){
                Price = price;
                Quantity = quantity;
            }
            // getters and setters
            public int getPrice(){return Price;}
            public int getQuantity(){return Quantity;}

            //methods
            public int multiplyPQ(){
                return Price*Quantity;
            }

        }

