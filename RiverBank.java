
/**
 * Subclass for Location objects of Riverbank type
 *
 * @Alexander Gardner
 * @10/12/2024
 */
public class RiverBank extends Map
{
    private int waterLevel; //0 = lowest, 1 = medium, 2 = high, specific to RiverBank Locations
    
    public RiverBank(String localeName, int waterLevel, boolean areaIsClear){
        super("RiverBank", localeName, areaIsClear);
        this.waterLevel = waterLevel; 
        
    }
    @Override
    public int getWaterLevel(){
        return waterLevel;
    }
    @Override
    public void printLocationInfo(){
        
        String place = getLocaleName();
        
        System.out.println("Your current world location is: " + place + ".");
        System.out.println("You are alongside River Murk, and must be mindful of enemies lurking beneath the surface...");
        
        
        
    }
}
