package Statistics;

class LinkList {
    //Defining the attributes
    public Link firstLink;
    public int Size = 0;
    //Constructor for linked list
    LinkList(){firstLink = null; }

    //Methods
    //checks if first link is empty, if it is then the value is returned true
    public boolean isEmpty(){
        if(firstLink == null){
            return true;
        }
        return false;
    }
    //adds candle to the linked list
    public void add(Candle candle){
        //new link is created
        Link newLink = new Link(candle, Size);
        //Attribute next of new link is set to first link
        newLink.next = firstLink;
        //the first link is now the linked that has been added
        firstLink = newLink;
        //the attribute size of the linked list increases by 1
        Size +=1;
    }
    //gets candle with the index of the item
    public Candle get(int x){
        //starts at the first link
        Link theLink = firstLink;
        //as long as it is not empty
        if(!isEmpty()){
            while(theLink.index != x){
                //as long as the current link is not equal to the index entered
                if(theLink.next == null){
                    //if the next link is equal to null then the candle is returned
                    return theLink.Candle;
                } else {
                    //else the current link is changed to the next
                    theLink = theLink.next;
                }

            }

        }
        //the current candle is returned
        return theLink.Candle;

    }
}
