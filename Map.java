
/**
 * Implements Location interface 
 *
 * @Alexander Gardner
 * @10/12/2024
 */

import java.util.Random;

abstract class Map implements Location
{
    //fields shared by all Location objects
    private String localeType;
    private String localeName;
    private boolean areaIsClear;
    
    //used for random generation of variable values in specific cases
    Random random = new Random();
    
    
    public Map(String localeType, String localeName, boolean areaIsClear){
        //constructor for map objects of location interface
        this.localeType = localeType;
        this.localeName = localeName;
        this.areaIsClear = areaIsClear;
    }
    public String getLocaleType(){
        return localeType;
    }
    public String getLocaleName(){
        return localeName;
    }
    public boolean getAreaIsClear(){
        return areaIsClear;
    }
    public void setAreaIsClear(){
        this.areaIsClear = areaIsClear;
    }
    public boolean isWitchPresent(){
        //randomly determines if witch is present in current location (swamps);
        boolean witchIsPresent = random.nextBoolean();
        if(witchIsPresent = true){
        return true;
        }else{
        return false;
        }
    }
    public int getWaterLevel(){
        //same issue as getTreeDensity, design issue of how hierarchy is laid out, should have interface for every "type" i think
        int waterLevel = random.nextInt(3);
        return waterLevel;
    }
    public int getTreeDensity(){
        //currently random, should be fixed value. Fix functionaity later, issue of hieracrhy access
        int treeDensity = random.nextInt(3);
        return treeDensity;
    }
    public boolean getShopExists(){
        return true;
    }
    public void printLocationInfo(){
        
    }
}