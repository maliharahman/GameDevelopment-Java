
package bestgameever;
//hello world

import java.io.IOException;
import java.util.ArrayList;

public class BestGameEver {
     
    
    public static void main(String[] args) throws IOException {
        // variable to read the menu choice
        int choice = 100;
        // future player pointer
        Player player = null;
        // string tellin what to choose now
        String choiceString;
        //additional class with menu functions
        MenuFactory menu = MenuFactory.getInstance();
       
        
        
        // do while a user does not want to stop (0=quit)
        while ( 0 != choice ){
            
            // starting menu deals with the player 
            // (create, print, delete, do sth with items)
            menu.printMenu();
            // read input in the form of integer
            choice = menu.getChoice();
            
            // choice cases
            switch ( choice ){
                // 1 = create player
                case 1 :
                    player = menu.createPlayer();     
                    // tell about an unsuccessful creation
                    System.out.println( player == null ? "Creation failed!\n" : 
                            "New player created!\n" );
                    break;
                // 2 = print a player
                case 2 : 
                    // do not print a player if it has not been created yet
                    if ( player == null ){
                        System.out.println("\nA player does not exist!\n");
                    }
                    else
                        // print existing player
                        player.printPlayer();
                    break;
                case 3 :
                    // delete an existing player and report about that
                    System.out.println( player == null ? "There is no player!\n" : 
                            "Player deleted\n" );
                    
                    // just delete once again to be on the safe side
                    player = null;
                    break;
                case 4 :
                    // 4 = play with items if a player exists
                    if ( player == null ){
                        System.out.println("\nA player does not exist\n");
                    }
                    else
                    {
                        // print items actions menu (add, delete, show, use 
                        menu.printPlayWithItemsMenu();
                        // use another choice variable not to lose 
                        // the previous choice of an upper level menu
                        int choicePlayItems = menu.getChoice();
                        // realize choices
                        switch ( choicePlayItems ){     
                            // 1 = add item
                            case 1 :
                                // ask which item to create
                                menu.printItemsMenu();
                                // create and add items
                                Item a = menu.createItem();
                                player.addItem(a);
//                                player.addItemHashMap(a);
                                break;
                            // 2 = delete an item
                            case 2 :
                                // show items first
                                System.out.println("Your items: ");
                                player.showInventoryShortly();
                                // remember current items list size
                                int currentSize = player.getInventory().getItems().size();
                                
                                // if items exist (size > 0), 
                                // choose number of an item from a list to delete
                                if (currentSize > 0){
                                    choiceString = "Choose item number to delete: ";
                                    // this number will be chosen out of 0..size-1 indices
                                    int num = menu.enterNum( choiceString, 1,
                                            currentSize);
                                    // delete
                                    player.deleteItem(num-1);
//                                    String itemName = menu.chooseItem(player.getInventory().getItemsHashMap());
//                                    player.deleteItemHashMap(itemName);
                                }
                                else{
                                    System.out.println("No items to delete!\n");
                                }
                                break;
                            // 3 = show items
                            case 3 :
                                player.showInventory();
                                break;
                            // 4 = use an item
                            case 4 :
                                // print a list of items to know their indices
                                System.out.println("Your items: ");
                                player.showInventoryShortly();
                                // remember the size
                                currentSize = player.getInventory().getSize();
                                // use an item if any items exist
                                if (currentSize > 0){
                                    // item is again chosen by its position in a list
                                    choiceString = "Choose item number to use: ";
                                    int num = menu.enterNum( choiceString, 1,
                                            currentSize);
                                    player.useItem(num-1);
                                    
//                                    String itemName = menu.chooseItem(player.getInventory().getItemsHashMap());
//                                    player.useItemHashMap(itemName);
                                }
                                else{
                                    System.out.println("No items to use!\n");
                                }
                                break;
                            case 5 :
                                Inventory expansionBookPack = menu.loadBookItems();
                                for(int i=0; i < expansionBookPack.getItems().size();i++){
                                    player.addItem(expansionBookPack.getItems().get(i));
                                }
                                Inventory expansionFoodPack = menu.loadFoodItems();
                                for(int i=0; i < expansionFoodPack.getItems().size();i++){
                                    player.addItem(expansionFoodPack.getItems().get(i));
                                }
                                Inventory expansionHelmetPack = menu.loadHelmetItems();
                                for(int i=0; i < expansionHelmetPack.getItems().size();i++){
                                    player.addItem(expansionHelmetPack.getItems().get(i));
                                }
                                Inventory expansionPotionsPack = menu.loadPotionsItems();
                                for(int i=0; i < expansionPotionsPack.getItems().size();i++){
                                    player.addItem(expansionPotionsPack.getItems().get(i));
                                }
                                Inventory expansionChestArmorPack = menu.loadChestArmorItems();
                                for(int i=0; i < expansionChestArmorPack.getItems().size();i++){
                                    player.addItem(expansionChestArmorPack.getItems().get(i));
                                }
                                break;
                                
                            // 0 = quit the menu of playing with items
                            case 0:
                                break;
                            default:
                                System.err.println( "Wrong choice!\n" );  
                        }
                    }
                    break;
                // 0 = quit the game
                case 0:
                    break;
                default:
                    System.err.println( "Wrong choice!\n" );    
            }
        }
    }
    
    
    /*
        //1.2f - f means that the number is expressed as float (4bytes) instead of double (8bytes)
        Item a = new Weapon( "Axe", "Some description", 1.2f, 42 );
        //If we want to use a as weapon, we have to cast it
        Weapon b = (Weapon)a;
        Player p = new Player();
        //Weapon is also item, so addItem() can be used on it
        p.addItem(a);
        try {
            BufferedReader reader = new BufferedReader( new FileReader( "data/weapons.csv" ));
            String line[];
            String wholeLine;
            //Here we read the "nameline" away
            reader.readLine();
            while( true ){
                wholeLine = reader.readLine();
                if( wholeLine == null )
                       break;
                line = wholeLine.split( "," );
                p.addItem(  new Weapon( line[0], line[1], Float.valueOf( line[2] ), Integer.valueOf( line[3])));
            }
            p.printItems();
            p.getInventory().printItems();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BestGameEver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex ){
            System.out.println("asdsadds");
        }
        */
    /*
    public useItem( int num ){
        Item item = inventory.getItem( num );
        if( item != null ){
            if( item instanceof Weapon ){
                Weapon a = (Weapon)item;
                a.attack();
            } else if( item instanceof Armor ){
                Armor a = (Armor)item;
                a.equip();
            }
        }
    }
    */
}
