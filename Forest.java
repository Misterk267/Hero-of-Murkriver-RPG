
/**
 * Class for Location Map objects of Forest type
 *
 * @Alexander Gardner
 * @10/12/2024
 */
public class Forest extends Map
{
    private int treeDensity; //0 = lowest, 1 = medium, 2 = high
    
    public Forest(String localeName, int treeDensity, boolean areaIsClear){
        super("Forest", localeName, areaIsClear);
        this.treeDensity = treeDensity; 
        
    }
    @Override
    public int getTreeDensity(){
        return treeDensity;
    }
    @Override
    public void printLocationInfo(){
        
        String place = getLocaleName();
        
        System.out.println("Your current world location is: " + place + ".");
        System.out.println("You are entering a wooded area, and must be wary of wolves.");
        
    }
    
}
