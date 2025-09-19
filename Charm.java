
/**
 * Subclass for Equippable objects of the Charm type
 *
 * @Alexander
 * @10/12/2024
 */
public class Charm extends Item
{
    public Charm(String equipmentName, String affectedStat, int statBonus, int itemId){
        super(equipmentName, "Charm", affectedStat, statBonus, itemId);
    }
    @Override
    public void listStat(){
        String stat = getAffectedStat();
        int bonus = getStatBonus();
        
        System.out.println("This Charm grants a bonus of " + bonus + " to " + stat + ".");
        System.out.println();
    }
}
