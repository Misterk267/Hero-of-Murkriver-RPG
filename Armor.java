
/**
 * Subclass for Equippable Items of Armor type
 *
 * @Alexander Gardner
 * @10/12/2024
 */

public class Armor extends Item
{
    public Armor(String equipmentName, String affectedStat, int statBonus, int itemId){
        super(equipmentName, "Armor", affectedStat, statBonus, itemId);
    }
    @Override
    public void listStat(){
        String stat = getAffectedStat();
        int bonus = getStatBonus();
        
        System.out.println("This armor grants a bonus of " + bonus + " to " + stat + ".");
        System.out.println();
    }
}