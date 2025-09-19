
/**
 * Interface for Equippable Items
 *
 * @Alexander Gardner
 * @10/12/2024
 */
public interface Equippable
{
    //lists items affected player statistic and its size
    public void listStat();
    
    //retrieves an item's effect size, if it has one
    public int getStatBonus();
    
    //retieves the type of equipment (weapon, armor, or charm)
    public String getEquipmentType();
    
    //retireves an item's effect
    public String getAffectedStat();
    
    //retrieves an item's name
    public String getEquipmentName();
    
    //retireves an item's id number, used for saving/retireving (Should use Map and key value instead, change if time)
    public int getItemId();
    
    //retireves an item's attack value, if any. Added to 1/2 player's primary attack stat to determine attack damage
    public int getAttackValue();
}

