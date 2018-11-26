package bestgameever;

public class ChestArmor extends Armor {
    // detailed constructor
    public ChestArmor( String name, int weight, int defence, int extraBonus ){
        super(name, weight, defence, extraBonus);
    }

    public void use(Stats stats){
        System.out.println(" You will wield Chest Armor ;) ");
        if (stats.isChestArmor()){
            unwield(stats);
        }
        else{
            wield(stats);
        }
    }

    // when a user uses the armor for the next time,
    // he/she changes the state of "wearing an armor"
    public void wield(Stats stats){
        stats.setChestArmor(true);
        stats.setBonus(stats.getBonus() + this.getExtraBonus());
        stats.setDefence(stats.getDefence() + this.getExtraDefence());
        System.out.print("You have wielded the Chest Armor : " + this.getName() );
    }

   public void unwield(Stats stats){
       stats.setChestArmor(false);
       stats.setBonus(stats.getBonus() - this.getExtraBonus());
       stats.setDefence(stats.getDefence() - this.getExtraDefence());
       System.out.print("You have unwielded the Chest Armor : " + this.getName() );
   }
}
