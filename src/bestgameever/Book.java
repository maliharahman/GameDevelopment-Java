package bestgameever;

public class Book extends Item {
    private int extraWisdom;
    
    // default constructor
    public Book(){
        super("Book", 0);
    }
    
    // detailed constructor    
    public Book( String name, int weight, int extraWisdom ){
        super(name, weight);
        this.extraWisdom = extraWisdom; 
    }
    
    public void use(Stats stats){
        stats.setWisdom (stats.getWisdom() + this.extraWisdom);
        System.out.println("You have read a book " + this.getName());
        System.out.println("Your current wisdom is " + stats.getWisdom());
    }
    
    public int getExtraWisdom(){
        return extraWisdom;
    }
    
    @Override
    public String toString(){
        String s = "Wisdom bonus: " + this.extraWisdom;
        return s;
    }
}
