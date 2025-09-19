
/**
 * Custom exception for 
 *
 * @Alexander Gardner
 * @10/10/24
 */
public class LocationOutofBoundsException extends RuntimeException
{
    private int location1;
    
    public LocationOutofBoundsException(String message, int location1){
        super(message);
        this.location1 = location1;
    }
    public int getLocation1(){
        return location1;
    }
    @Override
    public String toString(){
        return "LocationOutofBoundsException: " + "Tried to set coordinate to " + location1 + "." + " Playable locations include coordinates from 0 - 4 on either axis.";
        
    }


}
