/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestgameever;


public class Armor extends Item {

    private int defence;
    private int extraBonus;
    private String material;

    // detailed constructor
    public Armor(String name, int weight, int defence, int extraBonus){
//        (String name, String desc, float val,
//            int defence, String extraBonus, String material)
        super(name, weight); //(name,desc,val);
        this.defence = defence;
        this.extraBonus = extraBonus;
//        this.material = material;
        }


    // the function is redefined in the lower classes
    public void use(Stats stats){}

    public int getExtraDefence() {
        return defence;
    }

    public int getExtraBonus() {
        return extraBonus;
    }

    //public String getMaterial() {
    //    return material;
    //}

    @Override
    public String toString(){
        String s = "Bonus: " + extraBonus +
                "\nDefence: " + defence;
                //"\nMaterial: " + material;
        return s;
    }

}
