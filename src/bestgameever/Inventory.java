package bestgameever;

import java.util.ArrayList;
import java.util.HashMap;


public class Inventory {
    private ArrayList<Item> items;
    private int size;
    private int maxSize;
    private int currentWeight;
    private int maxWeight;
    private HashMap<String, Item> itemsHashMap;

    public Inventory() {
        items = new ArrayList<>();
        itemsHashMap = new HashMap<>();
        this.maxSize = 100;
        this.maxWeight = 100;
        this.size = 0;
        this.currentWeight = 0;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem( Item a ){
        // change the size of the list and the sum weight
        if (( this.currentWeight + a.getWeight() < this.maxWeight)&
                (this.size < this.maxSize)){
            this.size           += 1;
            this.currentWeight  += a.getWeight();
            items.add( a );
        if ( this.size > this.maxSize) {
              System.out.println("\n\n Your inventory is full");
            }
        }
        else{
            System.out.println("\n\nYour do not have space in your Inventory.");
            System.out.println("Current weight: " + this.currentWeight +
                    "\nItem weight: " + a.getWeight() +
                    "\nMax possible weight " + this.maxWeight);
            System.out.println("Items in Inventory: " + this.size +
                    " out of " + this.maxSize + ".\n\n");
        }
    }

    public void addItemHashMap( Item a ){
        // change the size of the list and the sum weight
        if (( this.currentWeight + a.getWeight() < this.maxWeight)&
                (this.size < this.maxSize)){
            this.size           += 1;
            this.currentWeight  += a.getWeight();
            this.itemsHashMap.put( a.getName().toLowerCase(), a );
        if ( this.size > this.maxSize) {
              System.out.println("\n\n Your inventory is full");
            }
        }
        else{
            System.out.println("\n\nYour do not have space in your Inventory.");
            System.out.println("Current weight: " + this.currentWeight +
                    "\nItem weight: " + a.getWeight() +
                    "\nMax possible weight " + this.maxWeight);
            System.out.println("Items in Inventory: " + this.size +
                    " out of " + this.maxSize + ".\n\n");
        }
    }
    
    public void useItem( int num, Stats stats ){
        // receive a pointer to a chosen item of the list
        Item item = items.get(num);

        // pass the player`s statistics to useItem function
        item.use(stats);
        // if you eat food or drink potion, remove them from the list
        if ((item instanceof Food)||(item instanceof Potion)){
            items.remove(num);
            this.currentWeight  -=item.getWeight();
            this.size           -=1;
        }
    }
    
    public void useItemHashMap( String name, Stats stats ){
        // receive a pointer to a chosen item of the list
        Item item = itemsHashMap.get(name);

        // pass the player`s statistics to useItem function
        item.use(stats);
        // if you eat food or drink potion, remove them from the list
        if ((item instanceof Food)||(item instanceof Potion)){
            itemsHashMap.remove(name);
            this.currentWeight  -=item.getWeight();
            this.size           -=1;
        }
    }

    public void deleteItem( int num, Stats stats ){
        if ( items.get(num) instanceof Helmet ){
            stats.setHelmet(false);
        }
        else if ( items.get(num) instanceof ChestArmor ){
            stats.setChestArmor(false);
    }
        items.remove(num);
    }

    public void deleteItemHashMap( String name, Stats stats ){
        if ( itemsHashMap.get(name) instanceof Helmet ){
            stats.setHelmet(false);
        }
        else if ( itemsHashMap.get(name) instanceof ChestArmor ){
            stats.setChestArmor(false);
    }
        items.remove(name);
        
    }
    
    public void showInventory() {
        // n - item index in a list
        int n = 1;
        // print the variables from item level here
        System.out.println("Inventory weight: " + this.currentWeight);
        System.out.println("Inventory size: " + this.size);
        // a loop of printing items with the help of override functions
        // (see inside every item realization)
        // BUT override function to_String does not allow to use "super"
        for( Item i : items ){
            System.out.println  ( "Item:" + n++ );
            System.out.println(i.getName() + "\n" + i +
                                "\nWeight: " + i.getWeight() + "\n");
        }
    }
    
    public void showInventoryShortly(){
        int n = 1;
        for( Item i : items ){
            System.out.println  ( "Item:" + n++ );
            System.out.println(i.getName() + "\n");
        }
    }
    
    public void showInventoryHashMap() {
        // n - item index in a list
        int n = 1;
        // print the variables from item level here
        System.out.println("Inventory weight: " + this.currentWeight);
        System.out.println("Inventory size: " + this.size);
        // a loop of printing items with the help of override functions
        // (see inside every item realization)
        // BUT override function to_String does not allow to use "super"
        for( Item i : items ){
            System.out.println  ( "Item:" + n++ );
            System.out.println(i.getName() + "\n" + i +
                                "\nWeight: " + i.getWeight() + "\n");
        }
    }
    
    public void showInventoryShortlyHashMap(){
        int n = 1;
        for( HashMap.Entry<String, Item> i : itemsHashMap.entrySet() ){
            System.out.println  ( "Item:" + n++ );
            System.out.println(i.getValue().getName() + "\n");
        }
    }
    
    public int getSize() {
        return size;
    }
    
    public HashMap<String, Item> getItemsHashMap() {
        return itemsHashMap;
    }

}