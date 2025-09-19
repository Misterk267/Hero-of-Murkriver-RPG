
/**
 * Subclass for Location objects of Riverbank type
 *
 * @Alexander Gardner
 * @initial
 */
public class Riverbank extends Map
{
    private int waterlevel; //0 = lowest, 1 = medium, 2 = high
    
    public Riverbank(String localename, int waterlevel, boolean areaisclear){
        super("Riverank", localename, areaisclear);
        this.waterlevel = waterlevel; 
        
    }
    public int getwaterlevel(){
        return waterlevel;
    }
    @Override
    public void printlocationinfo(){
        
        String place = getlocalename();
        
        System.out.println("Your current world location is: " + place + ".");
        System.out.println("You are alongside River Murk, and must be mindful of enemies lurking beneath the surface...");
        
        
        
    }
}
