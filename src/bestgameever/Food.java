package bestgameever;

public class Food extends Item {
    private int extraHealth;
    private int extraStrength;
    
    // default constructor
    Food(){
        super("food", 0);
        this.extraHealth = 0;
        this.extraStrength = 0;
    }
    
    // detailed constructor
    Food(String name, int weight, int extraHealth, int extraStrength){
        super(name, weight);
        this.extraHealth = extraHealth;
        this.extraStrength = extraStrength;
    } 
    
    // use and change stats
    public void use(Stats stats){
        stats.setCurrentHealth( stats.getCurrentHealth() + extraHealth );
        stats.setStrength( stats.getStrength() + extraStrength );
        if ( stats.getCurrentHealth() > stats.getMaxHealth() ){
            stats.setCurrentHealth( stats.getMaxHealth() );
        } 
        System.out.println("You have eaten "+ this.getName() +
                "\nYour current health is " + stats.getCurrentHealth() +
                "\nYour strength is " + stats.getStrength() );
    }
    
    @Override
    public String toString(){
        String s = "Health bonus: " + this.extraHealth + 
                "\nStrength bonus: " + this.extraStrength;
        return s;
    }
}
