package bestgameever;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MenuFactory {
    private static MenuFactory menuFactory;


    // private constructor
    private MenuFactory(){};

    // initialization + gettting instance/pointer to the class . This is a singleton.
    public static MenuFactory getInstance(){
        if (menuFactory == null){
            menuFactory = new MenuFactory();
            return (menuFactory);
        }
        else{
            return (menuFactory);
        }
    }

    // different menus - printing only

    public static void printMenu(){
        System.out.println( "\nChoose option you would like to do:" );
        System.out.println( "\t1) Create a player" );
        System.out.println( "\t2) Print a player" );
        System.out.println( "\t3) Delete a player" );
        System.out.println( "\t4) Play with items" );
        System.out.println( "\t0) Quit" );
    }

    public static void printPlayWithItemsMenu(){
        System.out.println("\nChoose option you would like to do:");
        System.out.println( "\t1) Create an item" );
        System.out.println( "\t2) Delete an item" );
        System.out.println( "\t3) Show your items" );
        System.out.println( "\t4) Use an item" );
        System.out.println( "\t5) Preload items from first expansion");


        System.out.println( "\t0) Back to player menu");
    }

    public static void printItemsMenu(){
        System.out.println("\nChoose your new item: ");
        System.out.println( "\t1) Armor" );
        System.out.println( "\t2) Potion" );
        System.out.println( "\t3) Food" );
        System.out.println( "\t4) Book" );
        System.out.println( "\t0) Back to play with items menu");
    }

    public static void printArmorMenu(){
            System.out.println("\nChoose your armor type: ");
            System.out.println("\t1) Chest armor");
            System.out.println("\t2) Helmet");
    }

    // function to get an integer number from a user
    public static int getChoice(){
        // line to read
        String line;
        // two additional variables
        Scanner scan;
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));

        // actual choice to return
        int choice;

        // do until an answer is integer
        while( true ){
            System.out.print( "Choice: " );
            try {
                // read and chack if it is integer
                line = reader.readLine();
                scan = new Scanner( line );
                choice = scan.nextInt();
                //Scanner scans only until it encounters space so second check
                //is needed for cases like "123 asd"
                if( !scan.hasNext())
                    break;
            } catch (IOException ex) {
                // exception: could not read
                System.err.println( "Error in reading input!" );
                choice = 0;
            } catch (Exception ex ){
                // excepion: not integer
                System.err.println( "Error in input!" );
            }
        }
        return choice;
    }

    // function requires to get an integer from a user console
    // which is between 0 and "max"
    // choice string = tells a user what he/she is choosing
    public static int enterNum(String choiceString, int min, int max){
        String line;
        Scanner scan;
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));
        // num = required integer
        int num;
        do{
            System.out.println(choiceString);
            System.out.print( "This parameter should be between " + min +
                    " and " + max + ": " );
            try {
                line = reader.readLine();
                scan = new Scanner( line );
                // check if the input is integer
                // if not assign num = -1 to pass the next if-statement corectly
                num = scan.hasNextInt() ? scan.nextInt() : -1;
                // check if num is within the range [0; max]
                if( num < min || num > max )
                    System.out.println( "Incorrect input." );
                else
                    break;
            } catch (IOException ex) {
                System.err.println( "Error in reading input!" );
            }
        } while( true );
        return (num);
    }


    public static Player createPlayer() {
        // auxiliary string variable
        String line;
        // future characteristics of a player
        String race;
        int currentHealth;
        int wisdom;
        int strength;
        // auxiliary vars
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));

        //Technically same as while-loop but the condition is evaluated at end.
        do{
            System.out.print( "\nChoose race: elf, ork, dwarf, human: " );
            try {
                line            = reader.readLine();
                // create a list of predefined races
                Set racesList   = new HashSet<String>();
                racesList.add ("elf");
                racesList.add ("ork");
                racesList.add ("dwarf");
                racesList.add ("human");

                // check if input race is in the list
                if ( racesList.contains( line.toLowerCase() ) ){
                    race = line.toLowerCase();
                    break;
                }
                else{
                    System.out.println("Wrong race choice! Please, try again");
                }
            } catch (IOException ex) {
                System.err.println( "Error in reading input!" );
            }
        } while( true );
        String choiceString;

        // parameters should be within an interval [0; 10?]
        choiceString    = "\nChoose wisdom.";
        wisdom          = enterNum(choiceString, 0, 10);

        choiceString    = "\nChoose current health.";
        currentHealth   = enterNum(choiceString, 0, 10);

        choiceString    = "\nChoose strength.";
        strength        = enterNum(choiceString, 0, 10);
        return new Player( race, wisdom, strength, currentHealth );
    }

    public static Item createItem(){
        // choose from the menu
        printItemsMenu();
        int choice      = getChoice();

        // future item
        Item newItem    = null;
        int weight      = 0;

        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));
        switch ( choice ){
            // 1 = armor
            case 1:
                weight          = enterNum( "\nChoose weight: ", 1, 24);
                int defence     = enterNum( "\nChoose defence: ", 0, 10);
                int extraBonus  = enterNum( "\nChoose extra bonus: ", 0, 10);

                // choose between chest armor and helmet
                printArmorMenu();

                int armorChoice = 0;
                while (0==armorChoice){
                    armorChoice = getChoice();
                    if ((armorChoice!=1)&(armorChoice!=2)){
                        armorChoice = 0;
                    }
                }

                // assign item name according to user choice
                String name = (armorChoice == 1)? "Chest armor": "Helmet";
                // create a corresponding type of armor
                newItem     = (armorChoice == 1)? new ChestArmor(name, weight,
                                defence, extraBonus): new Helmet(name, weight,
                                defence, extraBonus);
                // it writes "added", but truly the item is only created, not added yet
                System.out.println( "You have added " + name + " to your stock.");
                break;
            // 2 = portion
            case 2:
                // default name
                name        = "Potion";
                // user can assign anouther name
                System.out.println("\nChoose type of potion (any string): ");
                try{
                    name    = reader.readLine();
                } catch (IOException ex) {
                System.err.println( "Error in reading input!" );
                }

                weight              = enterNum( "\nChoose weight: ", 1, 24);
                int extraHealth     = enterNum( "\nChoose health points "
                        + "by potion type: ", 0, 10);
                int extraStrength   = enterNum( "\nChoose strength points"
                        + " by potion type: ", 0, 10);
                int extraWisdom     = enterNum( "\nChoose wisdom points "
                        + "by potion type: ", 0, 10);

                // create an item
                newItem             = new Potion( name, weight, extraHealth,
                        extraStrength, extraWisdom);
                System.out.println( "You have added " + name + " to your stock.");
                break;
            // 3 = food
            case 3:
                // default name
                name            = "Food";
                // another name if needed
                System.out.println("\nChoose type of food (any string): ");
                try{
                    name        = reader.readLine();
                } catch (IOException ex) {
                System.err.println( "Error in reading input!" );
                }

                weight          = enterNum( "\nChoose weight: ", 1, 24);
                extraHealth     = enterNum( "\nChoose health points "
                        + "by food type: ", 0, 10);
                extraStrength   = enterNum( "\nChoose strength points "
                        + "by food type: ", 0, 10);

                newItem         = new Food(name, weight, extraHealth,
                        extraStrength);
                break;
            // 4 = book
            case 4:
                name            = "Book";

                System.out.println("\nChoose type of book from the following categories: ");
                System.out.println("Choose from: Fiction, Adventure, Drama and Horror");
                
                try{
                    name = reader.readLine();
                    while(!("fiction".equalsIgnoreCase(name) || "adventure".equalsIgnoreCase(name) || "drama".equalsIgnoreCase(name) || "horror".equalsIgnoreCase(name)))
                 {
                     System.out.println("Valid book types are: Fiction, Adventure, Drama and Horror");
                     System.out.println("\nChoose type of book from these categories: ");
                     name=reader.readLine();
                 }
                } catch (IOException ex) {
                System.err.println( "Error in reading input!" );
                }
                weight          = enterNum( "\nChoose weight: ", 1, 24);
                extraWisdom     = enterNum( "\nChoose wisdom points "
                        + "by book type: ", 0, 10);
                newItem         = new Book( name, weight, extraWisdom );
                break;
            case 0:
                break;
            default:
                System.err.println( "Wrong choice!\n" );
        }
        return newItem;
    }

    public static Inventory loadBookItems() throws IOException{
        // Loading of future item
        Inventory newBookPack = new Inventory();
        Book newItem = null;
        String fileName = "data/Book.csv";
        BufferedReader reader = null;
        // Delimiter to read CSV file
        String COMMA_DELIMITER =",";
        //File file = new File(fileName); // read File itself
        try {
            // Reading the csv file
            reader = new BufferedReader(new FileReader(fileName));
            //Create List for holding Employee objects
            //List<Item> ItemList = new ArrayList<Item>();

            String line ="";

            reader.readLine(); // ignore the first line
            while( (line = reader.readLine()) != null){
                //line = reader.readLine();
                String[] itemDetails = line.split(COMMA_DELIMITER); // gets a whole line
                System.out.println(itemDetails[0]);
                if(itemDetails.length>0){
                    // Save the employee details in Employee object
                String name = itemDetails[0];
                Integer weight =  Integer.valueOf(itemDetails[1]);
                Integer extraWisdom = Integer.valueOf(itemDetails[2]);
                newItem = new Book(name, weight, extraWisdom);
//                System.out.println("Item Created");
                newBookPack.addItem(newItem);
//                System.out.println("Item added");
                }
            }
            // Printing the Item List from the Expansion Pack
            //for(Item e : newInventory.getItems())
            //{
            //    System.out.println(e.getName() + " " + e.getWeight() + "  "
            //                       + e.getExtraWisdom());
            //}
                //String values[] = wholeLine.split(",");
                //System.out.println(Arrays.toString(values));
                //for (int i=0 ;i < line.length; i++)
                //String name = values[0];
                //int weight = Integer.valueOf(values[1]);
                //int extraWisdom = Integer.valueOf(values[2]);
        } catch(Exception ee){
          ee.printStackTrace();
        }
        finally{
            try {
                reader.close();
            }
            catch(IOException ie){
                System.out.println("Error occured while closing the Buffered Reader");
                ie.printStackTrace();
            }

            //Logger.getLogger(BestGameEver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newBookPack;
        }

        public static Inventory loadFoodItems() throws IOException{
        // Loading of future item
        Inventory newFoodPack = new Inventory();
        Food newItem = null;
        String fileName = "data/Food.csv";
        BufferedReader reader = null;
        // Delimiter to read CSV file
        String COMMA_DELIMITER =",";
        //File file = new File(fileName); // read File itself
        try {
            // Reading the csv file
            reader = new BufferedReader(new FileReader(fileName));
            //Create List for holding Employee objects
            //List<Item> ItemList = new ArrayList<Item>();
            String line ="";
            reader.readLine(); // ignore the first line
            while( (line = reader.readLine()) != null){
                //line = reader.readLine();
                String[] itemDetails = line.split(COMMA_DELIMITER); // gets a whole line
                System.out.println(itemDetails[0]);
                if(itemDetails.length>0){
                    // Save the employee details in Employee object
                String name = itemDetails[0];
                Integer weight =  Integer.valueOf(itemDetails[1]);
                Integer extraHealth = Integer.valueOf(itemDetails[2]);
                Integer extraStrength = Integer.valueOf(itemDetails[3]);
                newItem = new Food(name, weight, extraHealth, extraStrength);
//                System.out.println("Item Created");
                newFoodPack.addItem(newItem);
//                System.out.println("Item added");
                }
            }
        } catch(Exception ee){
          ee.printStackTrace();
        }
        finally{
            try {
                reader.close();
            }
            catch(IOException ie){
                System.out.println("Error occured while closing the Buffered Reader");
                ie.printStackTrace();
            }
            //Logger.getLogger(BestGameEver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newFoodPack;
        }

        public static Inventory loadHelmetItems() throws IOException{
        // Loading of future item
        Inventory newHelmetPack = new Inventory();
        Helmet newItem = null;
        String fileName = "data/Helmet.csv";
        BufferedReader reader = null;
        // Delimiter to read CSV file
        String COMMA_DELIMITER =",";
        //File file = new File(fileName); // read File itself
        try {
            // Reading the csv file
            reader = new BufferedReader(new FileReader(fileName));
            //Create List for holding Employee objects
            //List<Item> ItemList = new ArrayList<Item>();
            String line ="";
            reader.readLine(); // ignore the first line
            while( (line = reader.readLine()) != null){
                //line = reader.readLine();
                String[] itemDetails = line.split(COMMA_DELIMITER); // gets a whole line
                System.out.println(itemDetails[0]);
                if(itemDetails.length>0){
                    // Save the employee details in Employee object
                String name = itemDetails[0];
                Integer weight =  Integer.valueOf(itemDetails[1]);
                Integer extraDefence = Integer.valueOf(itemDetails[2]);
                Integer extraBonus = Integer.valueOf(itemDetails[3]);
                newItem = new Helmet(name, weight, extraDefence, extraBonus);
//                System.out.println("Item Created");
                newHelmetPack.addItem(newItem);
//                System.out.println("Item added");
                }
            }
            // Printing the Item List from the Expansion Pack
            //for(Item e : newInventory.getItems())
            //{
            //    System.out.println(e.getName() + " " + e.getWeight() + "  "
            //                       + e.getExtraWisdom());
            //}
                //String values[] = wholeLine.split(",");
                //System.out.println(Arrays.toString(values));
                //for (int i=0 ;i < line.length; i++)
                //String name = values[0];
                //int weight = Integer.valueOf(values[1]);
                //int extraWisdom = Integer.valueOf(values[2]);
        } catch(Exception ee){
          ee.printStackTrace();
        }
        finally{
            try {
                reader.close();
            }
            catch(IOException ie){
                System.out.println("Error occured while closing the Buffered Reader");
                ie.printStackTrace();
            }
            //Logger.getLogger(BestGameEver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newHelmetPack;
        }

        public static Inventory loadPotionsItems() throws IOException{
        // Loading of future item
        Inventory newPotionsPack = new Inventory();
        Potion newItem = null;
        String fileName = "data/Potions.csv";
        BufferedReader reader = null;
        // Delimiter to read CSV file
        String COMMA_DELIMITER =",";
        //File file = new File(fileName); // read File itself
        try {
            // Reading the csv file
            reader = new BufferedReader(new FileReader(fileName));
            //Create List for holding Employee objects
            //List<Item> ItemList = new ArrayList<Item>();
            String line ="";
            reader.readLine(); // ignore the first line
            while( (line = reader.readLine()) != null){
                //line = reader.readLine();
                String[] itemDetails = line.split(COMMA_DELIMITER); // gets a whole line
                System.out.println(itemDetails[0]);
                if(itemDetails.length>0){
                    // Save the employee details in Employee object
                String name = itemDetails[0];
                Integer weight =  Integer.valueOf(itemDetails[1]);
                Integer extraHealth = Integer.valueOf(itemDetails[2]);
                Integer extraStrength = Integer.valueOf(itemDetails[3]);
                Integer extraWisdom = Integer.valueOf(itemDetails[4]);
                newItem = new Potion(name, weight, extraHealth, extraStrength,extraWisdom);
//                System.out.println("Item Created");
                newPotionsPack.addItem(newItem);
//                newPotionsPack.addItemHashMap(newItem);
//                System.out.println("Item added");
                }
            }

        } catch(Exception ee){
          ee.printStackTrace();
        }
        finally{
            try {
                reader.close();
            }
            catch(IOException ie){
                System.out.println("Error occured while closing the Buffered Reader");
                ie.printStackTrace();
            }
            //Logger.getLogger(BestGameEver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newPotionsPack;
        }

        public Inventory loadChestArmorItems(){
          // Loading of future item
        Inventory newChestArmorPack = new Inventory();
        ChestArmor newItem = null;
        String fileName = "data/chestArmor.csv";
        BufferedReader reader = null;
        // Delimiter to read CSV file
        String COMMA_DELIMITER =",";
        //File file = new File(fileName); // read File itself
        try {
            // Reading the csv file
            reader = new BufferedReader(new FileReader(fileName));
            //Create List for holding Employee objects
            //List<Item> ItemList = new ArrayList<Item>();
            String line ="";
            reader.readLine(); // ignore the first line
            while( (line = reader.readLine()) != null){
                //line = reader.readLine();
                String[] itemDetails = line.split(COMMA_DELIMITER); // gets a whole line
                System.out.println(itemDetails[0]);
                if(itemDetails.length>0){
                    // Save the employee details in Employee object
                String name = itemDetails[0];
                Integer weight =  Integer.valueOf(itemDetails[1]);
                Integer extraDefence = Integer.valueOf(itemDetails[2]);
                Integer extraBonus = Integer.valueOf(itemDetails[3]);
                newItem = new ChestArmor(name, weight, extraDefence, extraBonus);
//                System.out.println("Item Created");
                newChestArmorPack.addItem(newItem);
//                newChestArmorPack.addItemHashMap(newItem);
//                System.out.println("Item added");
                }
            }
        } catch(Exception ee){
          ee.printStackTrace();
        }
        finally{
            try {
                reader.close();
            }
            catch(IOException ie){
                System.out.println("Error occured while closing the Buffered Reader");
                ie.printStackTrace();
            }
            //Logger.getLogger(BestGameEver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newChestArmorPack;
        }
        
        public static String chooseItem ( HashMap<String, Item> itemsHashMap ){
            String name = "";
            BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));
            while ( null == itemsHashMap.get(name.toLowerCase()) ){
                try{
                    name = reader.readLine();
                } catch (IOException ex) {
                System.err.println( "Error in reading input!" );
                }
                System.out.println("\nNo such item. Please, try again.\n");
            }
            return name;
        }
        
    }
