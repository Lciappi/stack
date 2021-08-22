package Statistics;


public class Candle extends Product{
    //Atributes
    private String Code;
    //Constructor
    public Candle(int p, int q,String c) {
        super(p,q);
        Code = c;
    }
    //Methods
    public String getCode(){return Code;}
}
