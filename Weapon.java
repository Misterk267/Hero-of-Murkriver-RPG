
/**
 * Subclass for Equippable Weapons
 *
 * Alexander Gardner
 * @10/12/2024
 */
public class Weapon extends Item
{
    //field specific to Weapon objects
    private int attackValue;
    
    public Weapon(String equipmentName, String affectedStat, int statBonus, int attackValue, int itemId){
        super(equipmentName, "Weapon", affectedStat, statBonus, itemId);
        this.attackValue = attackValue;
    }
    @Override
    public void listStat(){
        //added to player stats
        System.out.println("This weapon deals " + getAttackValue() + " points of damage as a base.");
        System.out.println();
    }
    public int getAttackValue(){
        return attackValue;
    }
}
