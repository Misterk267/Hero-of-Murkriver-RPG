
/**
 * Implements Living 
 *
 * @Alexander Gardner
 * @Initial
 */
abstract class Entity implements Living
{
    private int maxHealth;
    private int currentHealth;
    private int dex;
    private int str;
    private int intl;
    private String type;
    
    
    public Entity(int maxHealth, int currentHealth, int dex, int str, int intl, String type){
        //constructor for all Living objects
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.dex = dex;
        this.str = str;
        this.intl = intl;
        this.type = type;
    }
   
    public void printStatus(){
        
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }
    public int getCurrentHealth(){
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth){
        this.currentHealth = currentHealth;
    }
    public int getDex(){
        return dex;
    }
    public void setDex(int dex){
        this.dex = dex;
    }
    public int getStr(){
        return str;
    }
    public void setStr(int str){
        this.str = str;
    }
    public int getIntl(){
        return intl;
    }
    public void setIntl(int intl){
        this.intl = intl;
    }
    public String getType(){
        return type;
    }
}
