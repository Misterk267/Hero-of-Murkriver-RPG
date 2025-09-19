
/**
 * Subclass for Living Enemy objects
 *
 * @Alexander Gardner
 * @Initial
 */
public class Enemy extends Entity
{
    //variables specific to enemies
    private boolean isAnimal;
    private String enemyName;
    private int damageDealt;
    
    public Enemy(String enemyName, int maxHealth, int currentHealth, int dex, int str, int intl, boolean isAnimal, int damageDealt){
        super(maxHealth, currentHealth, dex, str, intl, "Enemy");
        this.isAnimal = isAnimal;
        this.enemyName = enemyName;
        this.damageDealt = damageDealt;
    }
    @Override
    public void printStatus(){
        //determines percentage of enemy's remaining health
        double solution = getCurrentHealth() / getMaxHealth();
        String name = getEnemyName();
        
        if (solution >= 0.7){
            System.out.println("The " + name + " appears healthy.");
        }
        else if (solution > 0.3 && solution < 0.7){
            System.out.println("The " + name + " appears wounded.");
        }
        else if (solution <= 0.3 && solution > 0){
            System.out.println("The " + name + " is near death.");
        }
        else if (solution <= 0){
            System.out.println("The " + name + " has been slain!");
        }
            
        }
    
    public boolean getEnemyType(){
        return isAnimal;
    }
    public String getEnemyName(){
        return enemyName;
    }
    public int getDamageDealt(){
        return damageDealt;
    }
    public void heal(){
         //resets Enemy object health pools to maximum, used when spawning in Enemy objects so that they are ready for battle every time they are reused
         if (getCurrentHealth() < getMaxHealth()){
             setCurrentHealth(getMaxHealth());
         }
        
    }
    }
        
        
        
    
    

