package bestgameever;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public abstract class Item {
    private String name;
    private int weight;
//    private String description;
//    private float value;

    // constructor
    public Item( String name, int weight ){
//( String name, String desc, float val ){
        this.name = name;
        this.weight = weight;
//        this.description = desc;
//        this.value = val;
    }

    // this function is used inside inventory.useItem(num, stats)
    // the item is already called by its num in the list, only stats needed
    public void use (Stats stats){
        // if any function is not realized in a class which inherits item class:
        System.out.println("Function currently unavailable");
    };

    public int getWeight() {
        return weight;
    }

    public String getName() {
      return name;
    }
    
//    public String getDescription() { return description;}
//    public float getValue() { return value;}

    // just in case, if realizations of the class do not have override funciton
    @Override
    public String toString(){
    return "\n" + name;
    }


}
