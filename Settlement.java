
/**
 * Subclass for Location objects of Settlement type
 *
 * @Alexander Gardner
 * @10/12/2024
 */
public class Settlement extends Map
{
    private boolean shopExists;
    
    public Settlement(String localeName, boolean shopExists, boolean areaIsClear){
        super("Settlement", localeName, areaIsClear);
        this.shopExists = shopExists;
        
    }
    public boolean getShopExists(){
        return shopExists;
    }
    public void setShopExists(){
        this.shopExists = shopExists;
    }
    @Override
    public void printLocationInfo(){
        String place = getLocaleName();
        
        System.out.println("Your current world location is: " + place + ".");
        System.out.println("You are entering a settled area. Your health and heals have been refilled.");
        
    }
}
