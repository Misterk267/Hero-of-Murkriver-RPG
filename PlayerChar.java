
/**
 * Subclass for Living player characters
 *
 * @Alexander Gardner
 * 10/12/2024
 */

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlayerChar extends Entity
{
    Scanner articulator = new Scanner(System.in);
    
    private ArrayList<Equippable> inventory = new ArrayList<>();
    
    //field to hold player's class decision (which type of charcater they chose to play)
    private String playerClass;
    
    //player fields relating to other custom objects
    private Equippable equippedArmor;
    private Equippable equippedWeapon;
    private Equippable equippedCharm;
    private Enemy currentEnemy;
    
    //player fields related to integers such as counter variables
    private int locationX;
    private int locationY;
    private int level;
    private int heals;
    private int baseHealth;
    private int enemiesDefeated;
    private int attackBonus;
    private int gold;
    private int baseDex;
    private int baseStr;
    private int baseIntl;
    
    //set if player defeats the final boss and wins
    private boolean hasRelic;

    public PlayerChar(int baseHealth, int maxHealth, int currentHealth, int dex, int str, int intl, String playerClass, Equippable equippedArmor, Equippable equippedWeapon, Equippable equippedCharm, int level, int heals, int locationX, int locationY, int enemiesDefeated, int attackBonus, int gold, boolean hasRelic, Enemy currentEnemy, int baseStr, int baseDex, int baseIntl){
        //constructor for all player object fields
        super(maxHealth, currentHealth, dex, str, intl, "PlayerChar" );
        this.equippedArmor = equippedArmor;
        this.equippedWeapon = equippedWeapon;
        this.equippedCharm = equippedCharm;
        this.inventory = inventory;
        this.level = level;
        this.playerClass = playerClass;
        this.heals = heals;
        this.locationX = locationX;
        this.locationY = locationY;
        this.baseHealth = baseHealth;
        this.enemiesDefeated = enemiesDefeated;
        this.attackBonus = attackBonus;
        this.gold = gold;
        this.hasRelic = hasRelic;
        this.currentEnemy = currentEnemy;
        this.baseDex = baseDex;
        this.baseStr = baseStr;
        this.baseIntl = baseIntl;
    }
    public Equippable getEquippedArmor(){
        return equippedArmor;
    }
    public int getBaseStr(){
        return baseStr;
    }
    public void setBaseStr(int baseStr){
        this.baseStr = baseStr;
    }
    public int getBaseDex(){
        return baseDex;
    }
    public void setBaseDex(int baseDex){
        this.baseDex = baseDex;
    }
    public int getBaseIntl(){
        return baseIntl;
    }
    public void setBaseIntl(int baseIntl){
        this.baseIntl = baseIntl;
    }
    public void setEquippedArmor(Equippable equippedArmor){
        this.equippedArmor = equippedArmor;
    }
    public Equippable getEquippedWeapon(){
        return equippedWeapon;
    }
    public void setEquippedWeapon(Equippable equippedWeapon){
        this.equippedWeapon = equippedWeapon;
    }
    public Equippable getEquippedCharm(){
        return equippedCharm;
    }
    public void setEquippedCharm(Equippable equippedCharm){
        this.equippedCharm = equippedCharm;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public String getPlayerClass(){
        return playerClass;
    }
    public void setPlayerClass(String playerClass){
        this.playerClass = playerClass;
    }
    public int getHeals(){
        return heals;
    }
    public void setHeals(int heals){
        this.heals = heals;
    }
    public int getBaseHealth(){
        return baseHealth;
    }
    public void setBaseHealth(int baseHealth){
        this.baseHealth = baseHealth;
    }
    public ArrayList<Equippable> getInventory(){
        return inventory;
    }
    public void clearInventory(){
        inventory.clear();
    }
    public void removeItem(Equippable item){
        inventory.remove(item);
    }
    public void addItem(Equippable item){
        inventory.add(item);
    }
    public int getEnemiesDefeated(){
        return enemiesDefeated;
    }
    public void setEnemiesDefeated(int enemiesDefeated){
        this.enemiesDefeated = enemiesDefeated;
    }
    
    public Enemy getCurrentEnemy(){
        return currentEnemy;
    }
    public void setCurrentEnemy(Enemy currentEnemy){
        this.currentEnemy = currentEnemy;
    }
    public int heal(){
        //allows player to use health potions
        
        int potions = getHeals();
        int chealth = getCurrentHealth();
        int mhealth = getMaxHealth();
        int difference = 0;
        int other = 0;
        
        if (potions <= 0){
            System.out.println("Sorry, but you are out of health potions.");
        }else{
            chealth = chealth + 15;
            if (chealth > mhealth){
                difference = chealth - mhealth;
                chealth = chealth - difference;
                other = 15 - difference;
                System.out.println("You restored " + other + " points of health.");
                System.out.println();
            }else{
                System.out.println("You restored 15 points of health.");
                System.out.println();
            }
        }
        setHeals(potions - 1);
        setCurrentHealth(chealth);
        return chealth;
    }
    public int getLocationX(){
        return locationX;
    }
    public void setLocationX(int locationX){
        this.locationX = locationX;
    }
    public int getLocationY(){
        return locationY;
    }
    public void setLocationY(int locationY){
        this.locationY = locationY;
    }
    public int getGold(){
        return gold;
    }
    public void setGold(int gold){
        this.gold = gold;
    }
    public int getAttackBonus(){
        return attackBonus;
    }
    public void setAttackBonus(int attackBonus){
        this.attackBonus = attackBonus;
    }
    public boolean getHasRelic(){
        return hasRelic;
    }
    public void setHasRelic(boolean hasRelic){
        this.hasRelic = hasRelic;
    }
    @Override
    public void printStatus(){
        //prints all character details for the player
        Equippable armor = getEquippedArmor();
        Equippable weapon = getEquippedWeapon();
        Equippable charm = getEquippedCharm();
        
        String armor1;
        String weapon1;
        String charm1;
        
        
        if (armor != null){
            armor1 = armor.getEquipmentName();
        }else{
            armor1 = "None";
        }
        
        if (weapon != null){
            weapon1 = weapon.getEquipmentName();
        }else{
            weapon1 = "None";
        }
        
        if (charm != null){
            charm1 = charm.getEquipmentName();
        }else{
            charm1 = "None";
        }
       
        System.out.println("Player Status:");
        System.out.println("Class: " + getPlayerClass() + " Level: " + getLevel());
        System.out.println("Health: " + getCurrentHealth() + "/" + getMaxHealth());
        System.out.println("Dexterity: " + getDex());
        System.out.println("Strength: " + getStr()); 
        System.out.println("Intelligence: " + getIntl());
        System.out.println("Attack Damage: " + getAttackBonus());
        System.out.println("Equipped Items:");
        System.out.println("Armor: " + armor1);
        System.out.println("Weapon: " + weapon1);
        System.out.println("Charm: " + charm1);
        System.out.println("You have " + getGold() + " pieces of gold.");
        System.out.println("You have defeated " + getEnemiesDefeated() + " enemies.");
        System.out.println("Remaining potions: " + getHeals());
        System.out.println();
        
    }
}
