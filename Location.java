
/**
 * Interface for Location objects
 *
 * @Alexander Gardner
 * @10/12/2024
 */
public interface Location
{
    //prints info about current location
    abstract void printLocationInfo();
    
    //gets type of Location (swamp, forest, settlement, etc.)
    abstract String getLocaleType();
    
    //gets name of Location
    abstract String getLocaleName();
    
    //gets wether the area has completed this area yet - currently unused for replayability
    abstract boolean getAreaIsClear();
    
    //updates area's status, used when player finishes an area
    abstract void setAreaIsClear();
    
    //determines whether a witch is present in a swamp (this mechanic is unique to swamps, should fix this)
    abstract boolean isWitchPresent();
    
    //retrieves whether a shop exists in a settlement, currently always true
    abstract boolean getShopExists();
    
    //retrieves how deep the river of a RiverBank area is
    abstract int getWaterLevel();
    
    //retrieves how thick the trees are in a Forest, used to set atmosphere, currently random
    abstract int getTreeDensity();
    
}
