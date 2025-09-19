
/**
 * Implents Equippable items for players
 *
 * @Alexander Gardner
 * @10/12/2024
 */
abstract class Item implements Equippable
{
    //all fields common to Equippable objects
    private String equipmentName; //name of the item, player-facing
    private String equipmentType; //Armor, Weapon, Charm
    private String affectedStat; //Bonus to health, dex, or str etc.
    private int statBonus; //size of stat bonus when equipped
    private int itemId; // left over from previous implementation, unused
    private int attackValue; //only here so weapon subclass works correctly ??
    
    public void liststat(){
        //implemented by subclass, unecessary to do this way, correct to single method here if time
    }
    public Item(String equipmentName, String equipmentType, String affectedStat, int statBonus, int itemId){
        //constructor for all player equippable objects
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.affectedStat = affectedStat;
        this.statBonus = statBonus;
        this.itemId = itemId;
    }
    public String getEquipmentType(){
        return equipmentType;
    }
    public String getAffectedStat(){
        return affectedStat;
    }
    public int getStatBonus(){
        return statBonus;
    }
    public String getEquipmentName(){
        return equipmentName;
    }
    public int getItemId(){
        return itemId;
    }
    public int getAttackValue(){
        return attackValue;
    }
}