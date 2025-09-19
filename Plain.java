
/**
 * Subclass of Location objects of Plain type
 *
 * @Alexander Gardner
 * @10/12/2024
 */
public class Plain extends Map
{
    
    public Plain(String localeName, boolean areaIsClear){
        super("Plain", localeName, areaIsClear);
        
    }
    @Override
    public void printLocationInfo(){
        String place = getLocaleName();
        
        System.out.println("Your current world location is: " + place + ".");
        System.out.println("You are entering a wide, open area and can see well into the distance.");
        
    }
}
