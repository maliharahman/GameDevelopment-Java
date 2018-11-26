/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestgameever;

/**
 *
 * @author aagri
 */
public class Potion extends Item{
    int extraHealth;
    int extraStrength;
    int extraWisdom;
    
    // default constructor
    public Potion(){
        super("Potion", 0);
        this.extraHealth = 0;
        this.extraStrength = 0;
        this.extraWisdom = 0;
    }
    
    // detailed constructor
    public Potion( String name, int weight, int extraHealth, 
            int extraStrength, int extraWisdom ){
        super(name, weight);
        this.extraHealth = extraHealth;
        this.extraStrength = extraStrength;
        this.extraWisdom = extraWisdom;
    }
    
    public void use(Stats stats){
        stats.setCurrentHealth  ( stats.getCurrentHealth() + this.extraHealth );
        stats.setStrength       (stats.getStrength() + this.extraStrength );
        stats.setWisdom         ( stats.getWisdom() + this.extraWisdom );
        System.out.println("You have drunk some potion");
    }
    
    @Override
    public String toString(){
        String s = "Health bonus: " + this.extraHealth + 
                "\nStrength bonus: " + this.extraStrength +
                "\nWisdom bonus: " + this.extraWisdom;
        return s;
    }
}
