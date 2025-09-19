
/**
 * Control Logic and implementation for Final Project - CIS150
 *
 * @Alexander Gardner
 * @10/13/2024
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Iterator;

public class FinalAssignment
{
    //variables related to menus
    public static boolean characterExists = false;
    public static int actionMenuSelect;
    public static int startMenuSelect;
    public static boolean levelUp;
    public static int combatChoice;
    public static boolean playerDeath;
    public static boolean enemyDeath;
    public static int activeMenu = 1;
    public static int classChoice;
    public static int attributeChoice;
    public static int shopSelection;
    public static int navigationChoice;
    public static int equipManagerChoice;
    public static int dropMenuChoice;
    public static int equipChangeChoice;
    public static int equipChoice;
    
    //creates game map memory
    public static Location [][] gameMap = new Location[5][5];
    
    //creates memory for all Equippable objects
    public static ArrayList<Equippable> gameItems = new ArrayList();

    public static void main(String[] args){
        Scanner articulator = new Scanner(System.in);
        
        //creates all Equippable Armor objects for later use
        Equippable knightsArmor = new Armor("Knight's Armor", "maxhealth", 5, 1);
        Equippable rangersArmor = new Armor("Ranger's Armor", "maxhealth", 3, 2);
        Equippable onyxArmor = new Armor("Onyx Armor", "maxhealth", 10, 3);
        Equippable darkArmor = new Armor("Dark Armor", "maxhealth", 9, 4);
        
        //creates all Equippable Weapon objects for later use
        Equippable knightSword = new Weapon("Knight's Sword", "none", 0, 4, 5);
        Equippable rangersBow = new Weapon("Ranger's Bow", "none", 0, 4, 6);
        Equippable onyxSword = new Weapon("Onyx Greatsword", "none", 0, 8, 7);
        Equippable darkBow = new Weapon("Dark Longbow", "none", 0, 8, 8);
        
        //creates all Equippable Charm objects for later use
        Equippable riverCharm = new Charm("Kraken's Visage", "dexterity", 10, 9);
        Equippable masque = new Charm("Masque of the Red Death", "intelligence", 10, 10);
        Equippable banditsBadge = new Charm("Bandit's Badge", "maxhealth", 10, 11);
        Equippable fleaInsignia = new Charm("Flea Insignia", "strength", 10, 12);
        
        //adds all Equippable items to object memory for use
        gameItems.add(knightsArmor);
        gameItems.add(rangersArmor);
        gameItems.add(onyxArmor);
        gameItems.add(darkArmor);
        
        gameItems.add(knightSword);
        gameItems.add(rangersBow);
        gameItems.add(onyxSword);
        gameItems.add(darkBow);
        
        gameItems.add(riverCharm);
        gameItems.add(masque);
        gameItems.add(banditsBadge);
        gameItems.add(fleaInsignia);
        
        //creates all standard Enemies players may face
        Enemy wolf = new Enemy("Wolf", 10, 10, 8, 10, 2, true, 2);
        Enemy witch = new Enemy("Witch", 12, 12, 15, 8, 10, false, 2);
        Enemy giantSpider = new Enemy("Giant Spider", 25, 25, 12, 10, 4, true, 4);
        Enemy livingWagon = new Enemy("Living Wagon", 30, 30, 2, 10, 5, false, 5);
        Enemy zombie = new Enemy("Zombie", 10, 10, 8, 3, 1, false, 1);
        Enemy direZombie = new Enemy("Dire Zombie", 14, 14, 8, 3, 1, false, 3);
        Enemy rat = new Enemy("Rat", 4, 4, 8, 4, 2, true, 1);
        Enemy giantRat = new Enemy("Giant Rat", 8, 8, 8, 4, 2, true, 4);
        Enemy bandit = new Enemy("Bandit", 12, 12, 8, 8, 6, false, 5);
        Enemy pirhanaSquid = new Enemy("Pirhana Squid", 15, 15, 10, 8, 6, true, 5);
        
        //creates player character, will populate fields later based on which character is chosen or by loading a character from file
        PlayerChar player = new PlayerChar(0, 0, 0, 0, 0, 0, "null", null, null, null, 0, 0, 0, 0, 0, 0, 0, false, rat, 0, 0, 0);
        
        //creates all boss enemies players may face
        Enemy charleMange = new Enemy("CharleMange, the Flea-Bitten", 80, 80, 20, 20, 10, false, 10);
        Enemy banditKing = new Enemy("Goddard, King of the Bandits", 50, 50, 14, 14, 8, false, 8);
        Enemy redDeath = new Enemy("The Scarlet Mist of Dread Swamp", 65, 65, 14, 2, 10, false, 10);
        Enemy krakan = new Enemy("River Krakan, Runt of the Litter", 38, 38, 20, 20, 10, true, 5);
        
        //creates first column of game map
        gameMap[0][0] = new Settlement("Castle MurkRiver", true, true);
        gameMap[0][1] = new Forest("Kantor Wood, North", 1, false);
        gameMap[0][2] = new Forest("Kantor Wood, South", 0, false);
        gameMap[0][3] = new Swamp("Dire Swamp North", false);
        gameMap[0][4] = new Swamp("Dire Swamp South", false);
        
        //creates second column of game map
        gameMap[1][0] = new Forest("Dark Woods, NorthWest", 2, false);
        gameMap[1][1] = new Forest("Dark Woods, SouthWest", 2, false);
        gameMap[1][2] = new Plain("Plains, NorthWest", false);
        gameMap[1][3] = new Plain("Plains, SouthWest", false);
        gameMap[1][4] = new Settlement("GreenWood Village", true, false);
        
        //creates third column of game map
        gameMap[2][0] = new Forest("Dark Woods, NorthEast", 2, false);
        gameMap[2][1] = new Forest("Dark Woods, SouthEast", 2, false);
        gameMap[2][2] = new Plain("Plains, NorthEast", false);
        gameMap[2][3] = new Plain("Plains, SouthEast", false);
        gameMap[2][4] = new Swamp("The Dreadful Swamp", false);
        
        //create fourth column of game map
        gameMap[3][0] = new RiverBank("Murk River Inlet", 2, false);
        gameMap[3][1] = new RiverBank("Murk River Main, North", 1, false);
        gameMap[3][2] = new RiverBank("Murk River Main, Central", 1, false);
        gameMap[3][3] = new RiverBank("Murk River Main, South", 1, false);
        gameMap[3][4] = new RiverBank("Murk River Outlet", 0, false);
            
        //creates fifth column of game map
        gameMap[4][0] = new Forest("Forbidden Wood", 2, false);
        gameMap[4][1] = new Swamp("Murk Marsh, North", false);
        gameMap[4][2] = new Swamp("Murk Marsh, Center", false);
        gameMap[4][3] = new Swamp("Murk Marsh, South", false);
        gameMap[4][4] = new Forest("Bandit Camp", 0, false);
        
        do{
            switch(activeMenu){
                case 1:{
                    startMenuSelect = startMenu(articulator);
                        switch(startMenuSelect){
                            case 1:{
                            
                            if (characterExists == false){
                            gameIntro();
                            }
                            characterSelection(articulator, player, gameItems);
                            
                            activeMenu = 2;
                            break;
                            }
                            case 2:
                            {
                            loadData(player, gameItems);
                            statModifier(player);
                            characterExists = true;
                            activeMenu = 2;
                            break;
                            }
                            case 3:
                            {
                            System.exit(0);
                            break;
                            }
                            default:
                            {
                                System.out.println("You have made an invalid selection. Please try again");
                                System.out.println();
                                activeMenu = 1;
                                break;
                            }
                        }
                
                    break;
                    }
                case 2: {
                    int actionMenuSelect = actionMenu(articulator, player);
                    switch (actionMenuSelect){
                        case 1: {
                            player.printStatus();
                            System.out.println();
                            break;
                        }
                        case 2: {
                            //calls equipment managemnt functions based on player choices, then calls statmodifier function to ensure player stats reflect changed equipment, if any
                            int equipManagerChoice = equipmentMenu(articulator);
                            switch(equipManagerChoice){
                                case 1:{
                                    printInventory(player);
                                    System.out.println();
                                    break;
                                }
                                case 2:{
                                    equipRemove(articulator, player);
                                    System.out.println();
                                    break;
                                }
                                case 3:{
                                    equipChange(articulator, player);
                                    System.out.println();
                                    break;
                                }
                            }
                            statModifier(player);
                            player.printStatus();
                            System.out.println();
                            break;
                        }
                        case 3: {
                            player.heal();
                            System.out.println();
                            break;
                        }
                        case 4: {
                            exploreArea(articulator, player, wolf, giantSpider, livingWagon, zombie, direZombie, rat, giantRat, bandit, pirhanaSquid, charleMange, banditKing, redDeath, krakan);
                            if (player.getCurrentEnemy() != null){
                            do{ //combat loop
                            enemyDeath = playerTurn(articulator, player);
                            if (enemyDeath == false){
                            playerDeath = enemyTurn(player);
                            }
                            }while (playerDeath == false && enemyDeath == false);
                            levelUp = combatOutcome(player, banditKing);
                            if (levelUp == true){
                                levelUp(articulator, player, attributeChoice);
                            }
                            }
                            break;
                        }
                        case 5:{
                            navigator(articulator, player, gameMap);
                            System.out.println();
                            break;
                        }
                        case 6:{
                            saveData(player, gameItems);
                            System.out.println();
                            break;
                        }
                        case 7:{
                            activeMenu = 1;
                            break;
                        }
                        case 8:{
                            System.out.println("You have asked for help.");
                            System.out.println();
                            System.out.println("The first option displays all current player statistics.");
                            System.out.println("This includes items such as your current and maximum health and equipped gear.");
                            System.out.println();
                            System.out.println("The second option allows you to change your equipped items and view you inventory items.");
                            System.out.println();
                            System.out.println("The third option uses a health potion from your inventory. This will restore a maximum of 15 points of your heath.");
                            System.out.println("Your remaining potions can be seen by selecting option 1.");
                            System.out.println();
                            System.out.println("The fourth option allows you to explore your current area.");
                            System.out.println("If your current area is a Settlement, you are safe and your health will be restored to full.");
                            System.out.println("If you have the gold, you can also purchase health potions in these locations.");
                            System.out.println("If your current location is not a settlement, selecting this option will generate a monster encounter.");
                            System.out.println("Defeating the monster will reward you with gold and experience.");
                            System.out.println("Every third monster you defeat will grant you a level, and you can increase one of your character's attributes permanently.");
                            System.out.println("Your current location and type are listed at the top of this menu.");
                            System.out.println();
                            System.out.println("The fifth option will open the navigation prompt, which lists nearby locations and allows you to navigate to them.");
                            System.out.println();
                            System.out.println("The sixth option will save your character and progress to a text file.");
                            System.out.println("This file can be loaded at any time at the game's first menu by selecting option 2.");
                            System.out.println();
                            System.out.println("And finally, the seventh option will return you to the game's first menu.");
                            System.out.println("Select this option if you want to start a new game, load a previous save, or quit the game entirely.");
                            System.out.println("Thanks for playing. Have Fun!");
                            System.out.println();
                                                        
                        }
                        default:{
                            System.out.println("You have entered an invalid selection. Please try again.");
                            System.out.println();
                            break;
                        }
                    }
                    break;
                }
            default: {
                    System.out.println("An error has ocurred. Returning to main menu.");
                    activeMenu = 1;
                    break;
                }
            }
        }while(activeMenu != -1); 
        }
    
    public static int startMenu(Scanner articulator){
        System.out.println("1. New Game");
        System.out.println("2. Load Character and Progress");
        System.out.println("3. Exit");
        
        startMenuSelect = articulator.nextInt();
        return startMenuSelect;
        
    }
    public static void gameIntro(){
        System.out.println("Welcome to Hero of MurkRiver!");
        System.out.println("You are a newly appointed retainer of the King's Guard, and have been tasked with locating and retrieving a sacred relic from the hands of thieves!");
        System.out.println("You must journey deep into the swamps and retrieve the item and prove yourself worthy of your station.");
        System.out.println("According to the king, 'you will know it when you see it'.");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("He looked embaressed...");
        System.out.println();
        
    }   
    public static void characterSelection(Scanner articulator, PlayerChar player, ArrayList<Equippable> gameItems){
        do{  
            System.out.println("Please choose your starting class.");
            System.out.println("This will affect what combat options are available to you and which gear you will find beneficial.");
            System.out.println();
            System.out.println("1. Knight:");
            System.out.println("These fighters enjoy head-on combat and are focused on melee.");
            System.out.println("Warriors start with heavier armor and a higher strength score.");
            System.out.println();
            System.out.println("2. Ranger: ");
            System.out.println("These fighters enjoy ranged combat and are focused on maintaining distance.");
            System.out.println("Rangers start with a bow and a higher dexterity score.");
            System.out.println();
            
            classChoice = articulator.nextInt();
        
            switch (classChoice){
                case 1:{
                    System.out.println("You have chosen the knight class.");
                    //sets player attributes to initial levels for Knight class
                    player.clearInventory(); // stops from loading old characters inventory items if new game is started with old character in memory
                    player.setBaseHealth(15);
                    player.setMaxHealth(20); 
                    player.setCurrentHealth(20);
                    player.setBaseDex(6);
                    player.setDex(6);
                    player.setBaseStr(10);
                    player.setStr(10);
                    player.setBaseIntl(6);
                    player.setIntl(6);
                    player.setPlayerClass("Knight");
                    //all objects for class (knight) given for testing, would normally get later
                    player.addItem(gameItems.get(0));
                    player.addItem(gameItems.get(2));
                    player.addItem(gameItems.get(4));
                    player.addItem(gameItems.get(6));
                    player.addItem(gameItems.get(8));
                    player.addItem(gameItems.get(9));
                    player.addItem(gameItems.get(10));
                    player.addItem(gameItems.get(11));
                    player.setEquippedArmor(gameItems.get(0));
                    player.setEquippedWeapon(gameItems.get(4));
                    player.setEquippedCharm(null);
                    player.setLevel(1);
                    player.setHeals(5);
                    player.setLocationX(0);
                    player.setLocationY(0);
                    player.setAttackBonus(9);
                    player.setGold(50);
                    
                    System.out.println("Knight's Armor and Knight's Sword have been added to your inventory and automatically equipped.");
                    
                    gameItems.get(1).listStat();
                    gameItems.get(5).listStat();
                    System.out.println();
                    
                    characterExists = true;
                    
                    break;
                }
                case 2:{
                    System.out.println("You have chosen the ranger class.");
                    //sets initial stats for ranger class
                    player.clearInventory();
                    player.setBaseHealth(15);
                    player.setMaxHealth(18); 
                    player.setCurrentHealth(18);
                    player.setBaseDex(10);
                    player.setDex(10);
                    player.setBaseStr(6);
                    player.setStr(6);
                    player.setBaseIntl(8);
                    player.setIntl(8);
                    player.setPlayerClass("Ranger");
                    player.addItem(gameItems.get(1));
                    player.addItem(gameItems.get(3));
                    player.addItem(gameItems.get(5));
                    player.addItem(gameItems.get(7));
                    player.addItem(gameItems.get(9));
                    player.addItem(gameItems.get(10));
                    player.addItem(gameItems.get(11));
                    player.setEquippedArmor(gameItems.get(1));
                    player.setEquippedWeapon(gameItems.get(5));
                    player.setEquippedCharm(null);
                    player.setLevel(1);
                    player.setHeals(5);
                    player.setLocationX(0);
                    player.setLocationY(0);
                    player.setAttackBonus(9);
                    player.setGold(50);
                    
                    System.out.println("Ranger's Armor and Ranger's Bow have been added to your inventory and automatically equipped.");
                    
                    gameItems.get(2).listStat();
                    gameItems.get(6).listStat();
                    System.out.println();
                    
                    characterExists = true;
                    
                    break;
                }
                default:{
                    System.out.println("You have chosen an invalid option. Please try again.");
                    characterExists = false;
                    break;
                }
            }
        }while(classChoice < 0 || classChoice > 2);
    }
    public static String localeNameResolver(Location[][] gameMap, PlayerChar player){
        //takes player coordinate variables and retrieves locale name map data for use
        int x = player.getLocationX();
        int y = player.getLocationY();
        
        Location t = gameMap[x][y];
        String locationName = t.getLocaleName();
        return locationName;
        
    }
    public static String localeTypeResolver(Location[][] gameMap, PlayerChar player){
        //takes player coordinate variables and retrieves locale type map data for use
        int x = player.getLocationX();
        int y = player.getLocationY();

        Location t = gameMap[x][y];
        String locationType = t.getLocaleType();
        return locationType;
        
    }
    public static int actionMenu(Scanner articulator, PlayerChar player){
        //Retrieves location details
        String name = localeNameResolver(gameMap, player);
        String type = localeTypeResolver(gameMap, player);
        
        //Gameplay Menu
        System.out.println("Your current location is " + name + " .");
        System.out.println("It is a " + type + ".");
        System.out.println("Please choose an action: ");
        System.out.println();
        System.out.println("1. Show Character Status");
        System.out.println("2. Equipment Manager");
        System.out.println("3. Use Health Potion");
        System.out.println("4. Explore Area");
        System.out.println("5. Navigate to Another Area");
        System.out.println("6. Save Character and Progress");
        System.out.println("7. Return to Main Menu");
        System.out.println("8. Print Help Menu");
        
        int actionMenuSelect = articulator.nextInt();
        return actionMenuSelect;
    }
    public static boolean playerTurn(Scanner articulator, PlayerChar player){
        Enemy enemy = player.getCurrentEnemy();
        int playerAttack = player.getAttackBonus();
        
        //displays player's current combat status
        System.out.println("Player Status: ");
        System.out.println("Health: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        System.out.println();
                
        //display's enemy's current combat status
        System.out.println("Enemy Status: ");
        System.out.println(enemy.getEnemyName() + ": " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
        //disabled, stretch goal to get this working enemy.printstatus();
        System.out.println();
        
        do{
            //combat menu, add flee option later
            System.out.println("Choose a combat action.");
            System.out.println("1. Attack");
            System.out.println("2. Use Health Potion");
            System.out.println();
                    
            combatChoice = articulator.nextInt();
        
            switch(combatChoice){
                case 1: {
                    if(player.getPlayerClass() == "Knight"){
                        System.out.println("You slash at the " + enemy.getEnemyName() + " with your sword.");
                    }else{
                        System.out.println("You fire an arrow into the " + enemy.getEnemyName() + " from your bow.");
                    }
                    System.out.println("You deal " + player.getAttackBonus() + " points of damage to the " + enemy.getEnemyName() + ".");
                    System.out.println();
                    enemy.setCurrentHealth(enemy.getCurrentHealth() - player.getAttackBonus());
                    player.setCurrentEnemy(enemy);
                    break;
                }
                case 2:{
                    player.heal();
                    break;
                }
                default:{
                    System.out.println("You have entered an invalid selection. Please try again.");
                    break;
                }
            }
        }while (combatChoice > 2 && combatChoice < 1);
        if(enemy.getCurrentHealth() > 0){
            return false;
        }else{
            return true;
        }
    }
    public static boolean enemyTurn(PlayerChar player){
        //fetches enemy information
        Enemy enemy = player.getCurrentEnemy();
        int damage;
        
        //sets to true if character dies
        boolean playerDeath;
        
        //creates randomness generators to modify combat variables for a different experience each time
        Random random = new Random();
        int damageModifier = random.nextInt(3);
        
        //should add field to enemy class
        switch(enemy.getEnemyName()){
            case "Wolf":{
                System.out.println("The wolf swipes at you with its claws.");
                System.out.println();
                break;
            }
            case "Giant Spider":{
                System.out.println("The spider bites at you with its mandibles.");
                System.out.println();
                break;
            }
            case "Living Wagon":{
                System.out.println("The Wagon charges, aiming to bowl you over.");
                System.out.println();
                break;
            }
            case "Zombie":{
                System.out.println("The zombie lurches forward, clumsily attempting to bite.");
                System.out.println();
                break;
            }
            case "Dire Zombie":{
                System.out.println("The zombie lurches forward, clumsily attempting to bite.");
                System.out.println();
                break;
            }
            case "Rat":{
                System.out.println("The large rat tries to bite your leg.");
                System.out.println();
                break;
            }
            case "Giant Rat":{
                System.out.println("The giant rat tries to bite at your torso.");
                System.out.println();
                break;
            }  
            case "Bandit":{
                System.out.println("The bandit slashes with his knife");
                System.out.println();
                break;
            }
            case "Pirhana Squid":{
                System.out.println("The squid slaps with its tentacle.");
                System.out.println();
                break;
            }
            case "Charlemange, the Flea-Bitten":{
                System.out.println("CharleMange charges, taking a mighty swing with his enormous sword.");
                System.out.println();
                break;
            }
            case "Goddard, King of the Bandits":{
                System.out.println("The King of Bandits charges, chopping at you with his axe.");
                System.out.println();
                break;
            }
            case "The Scarlet Mist of Dread Swamp":{
                System.out.println("The mist congeals around your form, squeezing your body.");
                System.out.println();
                break;
            }
            case "River Kraken, Runt of the Litter":{
                System.out.println("The Kraken swipes with a massive tentacle.");
                System.out.println();
                break;
            }
            default:{
                System.out.println("The " + enemy.getEnemyName() + " attacks.");
                break;
            }
    
        }
        //should generate 
        int missChance = random.nextInt(5);
        if(player.getPlayerClass() == "Ranger"){
            if(missChance < 2){
                System.out.println("The " + enemy.getEnemyName() + " missed!");
                System.out.println();
                damage = 0;
            }else{
                System.out.println("The " + enemy.getEnemyName() + " hits!");
                damage = enemy.getDamageDealt() + damageModifier;
            }
        }else{
            if(missChance < 3){
                System.out.println("The " + enemy.getEnemyName() + " missed!");
                System.out.println();
                damage = 0;
            }else{
                System.out.println("The " + enemy.getEnemyName() + " hits!");
                damage = enemy.getDamageDealt() + damageModifier;
            }
        }
        
        player.setCurrentHealth(player.getCurrentHealth() - damage);
        System.out.println(enemy.getEnemyName() + " deals " + damage + " points of damage.");
        System.out.println();
        
        if(player.getCurrentHealth() <= 0){
            return true;
        }else{
            return false;
        }
    
    }
    public static boolean combatOutcome(PlayerChar player, Enemy banditKing){
        boolean levelUp; //returns this if player meets requirements for a level increase
    
            if(player.getCurrentHealth() <= 0){
                playerDeath = true;
                System.out.println("You have died!");
                System.out.println("You have lost the game, but you can reload last save to try again.");
                System.out.println();
                characterExists = false;
                activeMenu = 1;
                levelUp = false;
            }else{
                Random random = new Random();
                int modifier = random.nextInt(3) + 1;
                int goldGained = modifier*5;
                int gold = player.getGold();
                
                //checks if player has defeated final boss, and prints victory menu if correct
                if(player.getCurrentEnemy() == banditKing){
                    System.out.println("Congratulations! You have defeated the final boss and retrieved the King's priceless treasure!");
                    System.out.println("You may continue playing as much as you like, or begin a new game.");
                    player.setHasRelic(true);
                }
                
                //prints victory dialogue
                System.out.println("Combat has ended in your victory.");
                System.out.println("You find " + goldGained + " pieces of gold.");
                System.out.println("You have gained experience.");
                System.out.println();
                
                player.setGold(gold + goldGained);
                
                //increments number of enemies defeated counter
                int wins = player.getEnemiesDefeated();
                int win = wins + 1;
                player.setEnemiesDefeated(win);
                
                //deterines if player is eligible to level up
                if (win % 3 == 0 && win > 0){
                    levelUp = true;
                }else{
                    levelUp = false;
                }
            
            }
        return levelUp;
    } 
        
    public static void exploreArea(Scanner articulator, PlayerChar player, Enemy wolf, Enemy giantSpider, Enemy livingWagon, Enemy zombie, Enemy direZombie, Enemy rat, Enemy giantRat, Enemy bandit, Enemy pirhanaSquid, Enemy charleMange, Enemy banditKing, Enemy redDeath, Enemy kraken){
        //old, needs rewritten badly
        //introduces areas and generates random events for some of them
        //monster intros should be fields of Enemy class
        
        int x = player.getLocationX();
        int y = player.getLocationY();
        
        Location t = gameMap[x][y];
        
        String localeName = t.getLocaleName();
        String localeType = t.getLocaleType();
        
        Random randomizer = new Random();
        int random = randomizer.nextInt(2);
        
        int gold = player.getGold();
        
        
        if (localeType == "Swamp" && localeName != "The Dreadful Swamp" && localeName != "Bandit Camp"){
            boolean witch = t.isWitchPresent();
            if(witch == true){
                System.out.println("You hear the cackling of a swamp witch nearby...");
                System.out.println("Monsters you encounter in this swamp will be stronger.");
                System.out.println();
                
                if (random == 0){
                    System.out.println("An enormous rat emerges from nearby trees, hissing at your unwelcome presence in its abode.");
                    System.out.println();
                    player.setCurrentEnemy(giantRat);
                    giantRat.heal(); //check enemy class for details
                }else{
                    System.out.println("A particularly tough-looking zombie rises from the swamp, lurching toward you and groaning.");
                    System.out.println();
                    player.setCurrentEnemy(direZombie);
                    direZombie.heal();
                }
                
            }else{
                System.out.println("Witches are common in swamps. Luckily, you see no signs of one this time.");
                System.out.println();
                if(random == 0){
                    System.out.println("A giant rat emerges from the nearby trees, hissing at your unwelcome presence.");
                    System.out.println();
                    player.setCurrentEnemy(rat);
                    rat.heal();
                }else{
                    System.out.println("A zombie rises from the swamp, lurching toward you.");
                    System.out.println();
                    player.setCurrentEnemy(zombie);
                    zombie.heal();
                }
            }
        }
        if (localeType == "Forest" && localeName != "Dire Wood" && localeName != "Bandit Camp"){
            int treeDensity = t.getTreeDensity();
            switch(treeDensity){
                case 0 : {
                    System.out.println("The trees here are spaced fairly far apart, and you can see well ahead of you.");
                    System.out.println();
                    break;
                }
                case 1: {
                    System.out.println("The trees in these woods are fairly close together, and it is difficult to see ahead at times.");
                    System.out.println();
                    break;
                }
                case 2: {
                    System.out.println("The trees in these wood are extremly close together, and it takes much more time to progress than normal.");
                    System.out.println("The thick canopy above severely limits visibility, and you find yourself frequently stumbling on roots.");
                    System.out.println();
                    break;
                }
                
            }
            if(random == 0){
                System.out.println("You hear the terrifying sound of many legs skittering across branches above you.");
                System.out.println("In front of you, a huge spider drops down on a strand of silk as thick as a rope.");
                System.out.println("It chitters and charges at you.");
                System.out.println();
                player.setCurrentEnemy(giantSpider);
                giantSpider.heal();
            }else{
                System.out.println("You hear barking and growling just behind you.");
                System.out.println("You turn to find an hungry-looking wolf emerging from the underbrush.");
                System.out.println("It growls loudly, and charges at you.");
                System.out.println();
                player.setCurrentEnemy(wolf);
                wolf.heal();
            }
        }
        if (localeType == "Riverbank" && localeName != "Murk River Outlet"){
            int waterLevel = t.getWaterLevel();
            switch(waterLevel){
                case 2: {
                    System.out.println("The water level at this portion of Murk River is very high.");
                    System.out.println("There is barely any room to walk between the river's edge and the treeline.");
                    System.out.println();
                    break;
                }
                case 1: {
                    System.out.println("The water level at this portion of Murk River is moderate.");
                    System.out.println("There is plenty of room to walk along the water's edge.");
                    System.out.println("Local legends say the river is at its most dangerous when the water level is lowest.");
                    System.out.println("There is nothing to worry about at the moment, save for small squid-like creatures.");
                    System.out.println("But best to keep an eye out.");
                    System.out.println();
                    break;
                }
            }
            System.out.println("Suddenly, a mass of gnashing teeth and flailing tentacles breaks the surface of the river, swiping at your feet.");
            System.out.println();
            player.setCurrentEnemy(pirhanaSquid);
            pirhanaSquid.heal();
        }
        if (localeType == "Plain"){
            System.out.println("Before you stretches a vast expanse of grasslands, with hardly a tree is sight.");
            if(random == 0){
                System.out.println("In the distance, you spot a wooden wagon, like the kind merchants use to transport goods.");
                System.out.println("Strangely, it appears to be moving toward you without any horses or a driver to control it.");
                System.out.println("You've heard of these living wagons, they can be very dangerous.");
                System.out.println("It charges toward you, aiming to smash into you.");
                System.out.println();
                player.setCurrentEnemy(livingWagon);
                livingWagon.heal();
            }else{
                System.out.println("You spot a ruffian moving toward you, a knife in hand.");
                System.out.println("He must aim to rob you.");
                System.out.println();
                player.setCurrentEnemy(bandit);
                bandit.heal();
            }
        }
        if (localeName == "Bandit Camp"){
            System.out.println("Before you is the deepest part of the woods in this area.");
            System.out.println("As you move along, the trees are suddenly thinning quickly, and before you know it, you are in a strange town.");
            System.out.println("The structures are hastily constructed wooden affairs, and the place in teeming with bandits.");
            System.out.println("As you approach, weapon in hand, the bandits move out of your way, as if they know you are here to challenge their leader.");
            System.out.println("At the end of of a main street is a large outhouse, and a massive main in gleaming armor emerges.");
            System.out.println("In one hand is a massive axe, in the other is a roll of the finest toilet paper you've ever laid eyes on.");
            System.out.println("The toilet paper appears to be made entirely of gold leaf, and its holder is engraved with priceless jewels.");
            System.out.println();
            System.out.println("And in that moment, you realize...");
            System.out.println("This is the holy relic you have been tasked to find.");
            System.out.println("The king of bandits meets your eye, setting the toilet paper down.");
            System.out.println("He shouts 'You'll never take it from me! It makes going to the bathroom so much better!'");
            System.out.println("He charges toward you, his eyes full of murderous intent.");
            System.out.println();
            player.setCurrentEnemy(banditKing);
            banditKing.heal();
        }
        if (localeName == "Forbidden Wood"){
            System.out.println("The wood here is the thickest you've seen, and you find yourself needing to cut through it to move forward.");
            System.out.println("You can hear a wolf-like growling up ahead, but it doesn't deter you.");
            System.out.println("The bandit's camp is rumored to be in a place like this.");
            System.out.println("A massive warrior steps forward through the fog, appearing like an eight-foot tall man with the head of a wolf.");
            System.out.println("Tufts of fur peek through the joints of his metala armor, and you can see fleas jumping about.");
            System.out.println("There are also patches of fur missing on his head.");
            System.out.println("He grins, revealing rows of razor-sharp teeth.");
            System.out.println("'This area is forbidden for a reason, you know.' he chuckles.");
            System.out.println("'For this is the home of I, CharleMange the Flea-bitten, and I do not like tresspassers.'");
            System.out.println("He charges forth, a massive sword held in both hands.");
            System.out.println("Instinctively, you know this will be the hardest fight you ever undertake.");
            System.out.println();
            player.setCurrentEnemy(charleMange);
            charleMange.heal();
        }
        if (localeName == "Murk River Outlet"){
            System.out.println("The water level here, where the river drains to the sea is at its lowest relative to the surrounding land.");
            System.out.println("This is the place where the River Kraken is rumored to live, with easy access to both the open water and the fish living in the river.");
            System.out.println("Predictably, a huge mass of gnashing teeth and flailing tentacles breaks the surface of the water.");
            System.out.println();
            player.setCurrentEnemy(kraken);
            kraken.heal();
        }
        if(localeName == "The Dreadful Swamp"){
            System.out.println("The marsh here is the deepest and most dangerous in the area");
            System.out.println("The air begins to hang heavy, and take on a red tinge.");
            System.out.println("There are strange red masses hanging among the midst.");
            System.out.println("You've heard of this, the Red Mist of Dreadful Swamp.");
            System.out.println("You'll have to damage the masses to get rid of it.");
            System.out.println();
            player.setCurrentEnemy(redDeath);
            redDeath.heal();
        }
        if (localeType == "Settlement"){
            player.setCurrentEnemy(null);
            System.out.println("You have entered a settlement and a local healer has tended to any wounds you may have.");
            System.out.println();
            
            if(player.getMaxHealth() > player.getCurrentHealth()){
            player.setCurrentHealth(player.getMaxHealth());
            }
   
            if (t.getShopExists() == true){
                do{
                    System.out.println("There is a shop selling health potions in this town.");
                    System.out.println("Would you like to purchase any while you are here?");
                    System.out.println("They cost five gold each.");
                    System.out.println();
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.println();
                
                    shopSelection = articulator.nextInt();
                    
                    switch(shopSelection){
                        case 1:{
                            if (player.getGold() < 5){
                                System.out.println("Sorry, but you cannot afford any potions at this time.");
                                System.out.println();
                            }else{
                                System.out.println("How many potions would you like to purchase?");
                                System.out.println();
                                int purchaseOrder = articulator.nextInt();
                                
                                if(player.getGold() < purchaseOrder*5){
                                    System.out.println("Sorry, but you cannot afford that many potions.");
                                    System.out.println();
                                }else{
                                    int remove = purchaseOrder*5;
                                    int newGold = player.getGold() - remove;
                                    player.setGold(player.getGold());
                                    int newHeals = player.getHeals() + purchaseOrder;
                                    player.setHeals(newHeals);
                                }
                            }
                            
                            break;
                        }
                        case 2:{
                        
                            break;
                        }
                        default:{
                        System.out.println("You have entered an invalid selection. Please try again.");
                        System.out.println();
                        }   
                    }
                }while(shopSelection != 1 && shopSelection != 2);
            }
        }
    }
    public static void saveData(PlayerChar player, ArrayList<Equippable> gameItems){
        //retrieves all player stats and inventory objects and converts to writeable format (String)
        String baseHealth = String.valueOf(player.getBaseHealth());
        String maxHealth = String.valueOf(player.getMaxHealth());
        String currentHealth = String.valueOf(player.getCurrentHealth());
        String baseDex = String.valueOf(player.getBaseDex());
        String baseStr = String.valueOf(player.getBaseStr());
        String baseIntl = String.valueOf(player.getBaseIntl());
        String locationX = String.valueOf(player.getLocationX());
        String locationY = String.valueOf(player.getLocationY());
        String level = String.valueOf(player.getLevel());
        String heals = String.valueOf(player.getHeals());
        String gold = String.valueOf(player.getGold());
        String hasRelic = String.valueOf(player.getHasRelic());
        String playerClass = player.getPlayerClass();
        
        ArrayList<Equippable> inventory = player.getInventory();
        
        int armor1;
        int weapon1;
        int charm1;
        
        //creates memory for conversion process
        Equippable armor = player.getEquippedArmor();
        Equippable weapon = player.getEquippedWeapon();
        Equippable charm = player.getEquippedCharm();
        
        if (armor != null){
            armor1 = gameItems.indexOf(armor); 
        }else{
            armor1 = 99;
        }
        if (weapon != null){
            weapon1 = gameItems.indexOf(weapon);
        }else{
            weapon1 = 99;
        }
        if (charm != null){
            charm1 = gameItems.indexOf(charm);
        }else{
            charm1 = 99;
        }
        
        //converts integer values for items to string
        String armor2 = String.valueOf(armor1);
        String weapon2 = String.valueOf(weapon1);
        String charm2 = String.valueOf(charm1);
        
        String fileName = "savedata.txt";
        
        //writes string lines to file
        try(FileWriter fw = new FileWriter(fileName, false);
        BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write(baseHealth);
            bw.newLine();
            bw.write(maxHealth);
            bw.newLine();
            bw.write(currentHealth);
            bw.newLine();
            bw.write(baseDex);
            bw.newLine();
            bw.write(baseStr);
            bw.newLine();
            bw.write(baseIntl);
            bw.newLine();
            bw.write(locationX);
            bw.newLine();
            bw.write(locationY);
            bw.newLine();
            bw.write(level);
            bw.newLine();
            bw.write(heals);
            bw.newLine();
            bw.write(gold);
            bw.newLine();
            bw.write(playerClass);
            bw.newLine();
            bw.write(armor2);
            bw.newLine();
            bw.write(weapon2);
            bw.newLine();
            bw.write(charm2);
            bw.newLine();
            bw.write(hasRelic);
            bw.newLine();
            
            for (Equippable item : inventory)
            {
                //converts inventory items to integer, then to string, writes to file
                int item1 = gameItems.indexOf(item);
                String item2 = String.valueOf(item1);
                bw.write(item2);
                bw.newLine();
                }
            
            System.out.println("Character saved to file successfully.");
            System.out.println();
            }
        
        catch(IOException i){
            i.printStackTrace();
            System.out.println("An error has ocurred while saving character to file.");
            }
        }
    public static void loadData(PlayerChar player, ArrayList<Equippable> gameItems){
        //load character from save file
        String filepath = "savedata.txt";
        boolean hasRelic1;
        
        try(FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr))
        {
            String baseHealth = br.readLine();
            String maxHealth = br.readLine();
            String currentHealth = br.readLine();
            String baseDex = br.readLine();
            String baseStr = br.readLine();
            String baseIntl = br.readLine();
            String locationX = br.readLine();
            String locationY = br.readLine();
            String level = br.readLine();
            String heals = br.readLine();
            String gold = br.readLine();
            String playerClass = br.readLine();
            String armor = br.readLine();
            String weapon = br.readLine();
            String charm = br.readLine();
            String hasRelic = br.readLine();
            
            int baseHealth2 = Integer.parseInt(baseHealth);
            int maxHealth2 = Integer.parseInt(maxHealth);
            int currentHealth2 = Integer.parseInt(currentHealth);
            int dex2 = Integer.parseInt(baseDex);
            int str2 = Integer.parseInt(baseStr);
            int intl2 = Integer.parseInt(baseIntl);
            int level2 = Integer.parseInt(level);
            int heals2 = Integer.parseInt(heals);
            int locationXX = Integer.parseInt(locationX);
            int locationYY = Integer.parseInt(locationY);
            int gold1 = Integer.parseInt(gold);
            
            if (locationXX < 0 || locationXX > 4){
                throw new LocationOutofBoundsException("X coordinate from file is outside map limits.", locationXX);
            }
            if (locationYY < 0 || locationYY > 4){
                throw new LocationOutofBoundsException("Y coordinate from file is outside map limits.", locationYY);
            }
            
            //begins setting player fields
            player.setBaseHealth(baseHealth2);
            player.setMaxHealth(maxHealth2);
            player.setCurrentHealth(currentHealth2);
            player.setBaseDex(dex2);
            player.setBaseStr(str2);
            player.setBaseIntl(intl2);
            
            //checks if location values are accurate
            try{
                player.setLocationX(locationXX);
            }catch(LocationOutofBoundsException uhoh){
                System.out.println("Error: Attempted to set player's X coordinate to invalid location during load operation.");
                System.out.println(uhoh.toString());
                System.out.println();
                player.setLocationX(0);
            }
            
            try{
                player.setLocationY(locationYY);
            }catch(LocationOutofBoundsException ohno){
                System.out.println("Error: Attempted to set player's Y coordinate to invalid value during load operation.");
                System.out.println(ohno.toString());
                System.out.println();
                player.setLocationY(0);
            }
            
            //continues setting player fields
            player.setLevel(level2);
            player.setHeals(heals2);
            player.setGold(gold1);
            player.setPlayerClass(playerClass);
            
            if (hasRelic == "true"){
                hasRelic1 = true;
            }else{
                hasRelic1 = false;
            }
            
            player.setHasRelic(hasRelic1);
            
            int armorID = Integer.parseInt(armor);
            int weaponID = Integer.parseInt(weapon);
            int charmID = Integer.parseInt(charm);
            
            Equippable armor1;
            Equippable weapon1;
            Equippable charm1;
            
            if(armorID != 99){
            armor1 = gameItems.get(armorID);
            }else{
            armor1 = null;
            }
            if(weaponID != 99){
            weapon1 = gameItems.get(weaponID);
            }else{
            weapon1 = null;
            }
            if(charmID != 99){
            charm1 = gameItems.get(charmID);
            }else{
            charm1 = null;
            }
            
            //sets player equipped items from file
            player.setEquippedArmor(armor1);
            player.setEquippedWeapon(weapon1);
            player.setEquippedCharm(charm1);
            
            String line;
            Equippable item;
            
            //retieves inventory from file
            while((line = br.readLine()) != null){
            int line1 = Integer.parseInt(line);
            item = gameItems.get(line1);
            player.addItem(item);
            }
            System.out.println("Character loaded from file successfully.");
            System.out.println();
        }catch(IOException i){
            i.printStackTrace();
            System.out.println("An error has ocurred while loading character from file.");
        }
    }
    public static int equipmentMenu(Scanner articulator){
        do{   
            //Presents menu for managing Equippable Items
            System.out.println("Please select an action: ");
            System.out.println("1. List All Inventory Items");
            System.out.println("2. Drop An Item");
            System.out.println("3. Change Equipped Items");
                    
            equipManagerChoice = articulator.nextInt();
            
        }while(equipManagerChoice < 1 && equipManagerChoice > 3);
        return equipManagerChoice;
    }
    public static void printInventory(PlayerChar player){
        //prints all items in inventory
        Equippable item;
        
        ArrayList<Equippable> inventory = player.getInventory();
        Iterator<Equippable> inventoryLooper = inventory.iterator();
        
        Equippable armor = player.getEquippedArmor();
        Equippable weapon = player.getEquippedWeapon();
        Equippable charm = player.getEquippedCharm();      
    
        while(inventoryLooper.hasNext()){
            item = inventoryLooper.next();
            System.out.println((inventory.lastIndexOf(item) + 1) + ". " + item.getEquipmentName());
            item.listStat();    
        }
    }
    public static void equipChange(Scanner articulator, PlayerChar player){ //changes equipped items for another in inventory
        do{
            //retieves equiped items
            Equippable armor = player.getEquippedArmor();
            Equippable weapon = player.getEquippedWeapon();
            Equippable charm = player.getEquippedCharm();
            
            Equippable item;
            
            //prints equiped items for reference
            if (player.getEquippedArmor() != null){
                System.out.println("Your currently equipped armor is " + armor.getEquipmentName() + ".");
                armor.listStat();
            }else{
                System.out.println("You have no armor equipped.");
                System.out.println();
            }
            if (player.getEquippedWeapon() != null){
                System.out.println("Your currently equipped weapon is " + weapon.getEquipmentName() + ".");
                weapon.listStat();
            }else{
                System.out.println("You have no weapon equipped.");
                System.out.println();
            }
            if (player.getEquippedCharm() != null){
                System.out.println("Your currently equipped charm is " + charm.getEquipmentName() + ".");
                charm.listStat();
            }else{
                System.out.println("You have no charm equipped.");
                System.out.println();
            }
                
            //menu for choosing type of equipment to change
            System.out.println("Which item would you like to change?");
            System.out.println("1. Armor");
            System.out.println("2. Weapon");
            System.out.println("3. Charm");
            System.out.println("Or,");
            System.out.println("4. Return to Game Menu");
            
            equipChangeChoice = articulator.nextInt();
            
            ArrayList<Equippable> inventory = player.getInventory();
            
            //memory for categorizing items in inventory
            ArrayList<Equippable> armors = new ArrayList();
            ArrayList<Equippable> weapons = new ArrayList();
            ArrayList<Equippable> charms = new ArrayList();
            
            //sorts inventory items into different areas of memory
            for(int a = 0; a < inventory.size(); a++){
                item = inventory.get(a);
                if(item.getEquipmentType() == "Armor"){
                    armors.add(item);
                }
                if(item.getEquipmentType() == "Weapon"){
                    weapons.add(item);
                }
                if(item.getEquipmentType() == "Charm"){
                    charms.add(item);
                }
            }
            
            //dear god was this part difficult to figure out
            //generates menus for items of each inventory type and prints menu for chosen equipment type
            switch(equipChangeChoice){
                case 1:{
                    System.out.println("Which armor would you like to equip?");
                    for (int a = 0; a < armors.size(); a++){
                        item = armors.get(a);
                        System.out.println((armors.indexOf(item) + 1) + ": " + item.getEquipmentName());
                    }
                    equipChoice = articulator.nextInt();
                    item = armors.get(equipChoice - 1);
                    player.setEquippedArmor(item);
                    break;
                }
                case 2:{
                    System.out.println("Which weapon would you like to equip?");
                    for (int a = 0; a < weapons.size(); a++){
                        item = weapons.get(a);
                        System.out.println((weapons.indexOf(item) + 1) + ": " + item.getEquipmentName());
                    }
                    equipChoice = articulator.nextInt();
                    item = weapons.get(equipChoice - 1);
                    player.setEquippedWeapon(item);
                    break;
                }
                case 3:{
                    System.out.println("Which charm would you like to equip?");
                    for (int a = 0; a < charms.size(); a++){
                        item = charms.get(a);
                        System.out.println((charms.indexOf(item) + 1) + ": " + item.getEquipmentName());
                    }
                    equipChoice = articulator.nextInt();
                    item = charms.get(equipChoice - 1);
                    player.setEquippedCharm(item);
                    break;
                }
                default:{
                    System.out.println("Returning to Game Menu...");
                    System.out.println();
                    break;
                }
            }
        }while(equipChangeChoice < 1 && equipChangeChoice > 3);
    }
    public static void equipRemove(Scanner articulator, PlayerChar player){ //allows player to drop inventory items
        //retrieves player inventory, creates iterator
        ArrayList<Equippable> inventory = player.getInventory();
        Iterator<Equippable> inventoryLooper = inventory.iterator();
        
        Equippable item;
        
        System.out.println("Which item would you like to drop?");
        
        for(int a = 0; a < inventory.size(); a++){
            item = inventory.get(a);
            System.out.println((a + 1) + ". " + item.getEquipmentName());
            
        }
        
        System.out.println();
        dropMenuChoice = articulator.nextInt();
        
        //measures inventory size prior to removing anything
        int size = inventory.size();
         
        while(inventoryLooper.hasNext()){
            item = inventoryLooper.next();
            if (inventory.indexOf(item) == (dropMenuChoice - 1)){
                if (inventory.size() == size){ //ensures multiple items are not removed, absolutely does without this
                inventoryLooper.remove();
                System.out.println(item.getEquipmentName() + " dropped.");
                
                //unequips item if it was equipped when player removed it from inventory
                if (item == player.getEquippedArmor()){
                    player.setEquippedArmor(null);
                }
                if(item == player.getEquippedWeapon()){
                    player.setEquippedWeapon(null);
                }
                if(item == player.getEquippedCharm()){
                    player.setEquippedCharm(null);
                }
                }
            }
        }
        
        
    }

    public static void statModifier(PlayerChar player){
        //updates player stats based on equipped items
        
        
        //declares gear variables
        
        //armor variables
        String armorAffectedStat;
        int armorStatBonus;
        
        //weapon variables
        int weaponAttackBonus;
        
        //charm variables
        String charmAffectedStat;
        int charmStatBonus;
        
        //retieves all player equipped items
        Equippable equippedArmor = player.getEquippedArmor();
        Equippable equippedWeapon = player.getEquippedWeapon();
        Equippable equippedCharm = player.getEquippedCharm();
        
        //retrieves player stats 
        int maxHealth = player.getBaseHealth();
        int str = player.getBaseStr();
        int dex = player.getBaseDex();
        int intl = player.getBaseIntl();
        
        //retrieves item parameters that modify player stats based on what is equipped
        
        if(equippedArmor != null){
            armorAffectedStat = equippedArmor.getAffectedStat();
            armorStatBonus = equippedArmor.getStatBonus();
        }else{
            armorAffectedStat = "none";
            armorStatBonus = 0;
        }
        
        if (equippedWeapon != null){
            weaponAttackBonus = equippedWeapon.getAttackValue();
        }else{
            weaponAttackBonus = 0;
        }
        
        if(equippedCharm != null){
            charmAffectedStat = equippedCharm.getAffectedStat();
            charmStatBonus = equippedCharm.getStatBonus();
        }else{
            charmAffectedStat = "none";
            charmStatBonus = 0;
        }
        
        //retrieves relevant player data
        String playerClass = player.getPlayerClass();
        
        //updates player stats based on equipment
        if(armorAffectedStat == "maxhealth"){
            maxHealth = maxHealth + armorStatBonus;
        }
        if (armorAffectedStat == "strength"){
            str = str + armorStatBonus;
        }
        if(charmAffectedStat == "maxhealth"){
            maxHealth = maxHealth + charmStatBonus;
        }
        if(charmAffectedStat == "dexterity"){
            dex = dex + charmStatBonus;
        }
        if(charmAffectedStat == "strength"){
            str = str + charmStatBonus;
        }
        if(charmAffectedStat == "intelligence"){
            intl = intl + charmStatBonus;
        }
        if (player.getCurrentHealth() > maxHealth){
            player.setCurrentHealth(maxHealth);
        }
        player.setMaxHealth(maxHealth);
        player.setDex(dex);
        player.setStr(str);
        player.setIntl(intl);
        if (playerClass == "Ranger"){
            int attackbns = player.getDex()/2 + equippedWeapon.getAttackValue();
            player.setAttackBonus(attackbns);
        }else{
            int attackbns = player.getStr()/2 + equippedWeapon.getAttackValue();
            player.setAttackBonus(attackbns);
        }
        }
    public static void levelUp(Scanner articulator, PlayerChar player, int attributeChoice){
        //allows player to increase statistics after defeating set numbers of enemies
        do{ 
            System.out.println("You have defeated " + player.getEnemiesDefeated() + " enemies.");
            System.out.println("Choose an attribute to increase.");
            System.out.println("If chosen, health will be increased by 3.");
            System.out.println("All other stats, if chosen, will be increased by 2.");
            System.out.println("1. Health");
            System.out.println("2. Dexterity");
            System.out.println("3. Strength");
            System.out.println();
            
            attributeChoice = articulator.nextInt();
            
            
            switch(attributeChoice){
                case 1:{
                    player.setBaseHealth(player.getBaseHealth() + 3);
                    player.setMaxHealth(player.getMaxHealth() + 3);
                    player.setCurrentHealth(player.getMaxHealth() + 3);
                    break;
                }
                case 2:{
                    player.setDex(player.getDex() + 2);
                    player.setBaseDex(player.getBaseDex() + 2);                
                    break;
                }
                case 3:{
                    player.setStr(player.getStr() + 2);
                    player.setBaseStr(player.getBaseStr() + 2);
                    break;
                }
                default:{
                    System.out.println("You have entered an invalid selection. Please try again.");
                }
            }
            
            player.setLevel(player.getLevel () + 1);
            
        }while(attributeChoice < 0 && attributeChoice > 4);
    }
    public static void navigator(Scanner articulator, PlayerChar player, Location gameMap[][]){
        //changes location
        System.out.println("Your current location is " + localeNameResolver(gameMap, player));
        System.out.println("You check your map.");
        System.out.println();
        
        int x = player.getLocationX();
        int y = player.getLocationY();
        int x1 = x - 1;
        int x2 = x + 1;
        int y1 = y - 1;
        int y2 = y + 1;
        
        if (x > 0){
            Location west = gameMap[x1][y];
            System.out.println("To the west lies the " + west.getLocaleType() + " of " + west.getLocaleName() + ".");
        }else{
            System.out.println("There is nothing of interest to the west.");
        }
        if (x < 4){
            Location east = gameMap[x2][y];
            System.out.println("To the east lies the " + east.getLocaleType() + " of " + east.getLocaleName() + ".");  
        }else{
            System.out.println("There is nothing of interest to the east.");
            System.out.println();
        }
        if (y > 0){
            Location north = gameMap[x][y1];
            System.out.println("To the north lies the " + north.getLocaleType() + " of " + north.getLocaleName() + ".");
        }else{
            System.out.println("There is nothing of interest to the north.");
        }
        if (y < 4){
            Location south = gameMap[x][y2];
            System.out.println("To the south lies the " + south.getLocaleType() + " of " + south.getLocaleName() + ".");
            System.out.println();
        }else{
            System.out.println("There is nothing of interest to the south.");
            System.out.println();
        }
        do{
        System.out.println();
        System.out.println("Please choose a movement option.");
        System.out.println("1. Stay at Present Location.");
        System.out.println("2. Move West");
        System.out.println("3. Move East");
        System.out.println("4. Move North");
        System.out.println("5. Move South");
        System.out.println();
        
        navigationChoice = articulator.nextInt();
        
        
        switch (navigationChoice){
            case 1: {
                System.out.println("You have chosen to stay at your present location.");
                System.out.println();
                break;
            }
            case 2: {
                if(x > 0){
                    System.out.println("You have chosen to move west.");
                    System.out.println();
                    player.setLocationX(x1);
                    player.setLocationY(y);
                }else{
                    System.out.println("That area is outside the playable map. Please select a different direction.");
                    System.out.println();
                }
                break;
            }
            case 3: {
                if (x < 4){
                    System.out.println("You have chosen to move east.");
                    System.out.println();
                    player.setLocationX(x2);
                    player.setLocationY(y);
                }else{
                    System.out.println("That area is outside the playable map. Please select a different direction.");
                    System.out.println();
                }
                break;
            }
            case 4: {
                if (y > 0){
                    System.out.println("You have chosen to move north.");
                    System.out.println();
                    player.setLocationX(x);
                    player.setLocationY(y1);
                }else{
                    System.out.println("That area is outside the playable map. Please select a different direction.");
                    System.out.println();
                }
                break;
            }
            case 5: {
                if (y < 4){
                    System.out.println("You have chosen to move south.");
                    System.out.println();
                    player.setLocationX(x);
                    player.setLocationY(y2);
                }else{
                    System.out.println("That area is outside the playable map. Please select a different direction.");
                    System.out.println();
                }    
                break;
            }
            default: {
                System.out.println("Sorry, but you have entered an invalid selection. Please try again.");
                System.out.println();
                break;
            }
        }
        }while (navigationChoice < 0 && navigationChoice > 5);
    
        
    }
}
