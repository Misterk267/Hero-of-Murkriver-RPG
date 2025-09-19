
/**
 * Class for Location Map objects of Forest type
 *
 * @Alexander Gardner
 * @10/12/2024
 */

import java.util.Random;

public class Swamp extends Map
{
    Random random = new Random();

    public Swamp(String localename, boolean areaisclear){
        super("Swamp", localename, areaisclear);
        
    }
    @Override
    public boolean isWitchPresent(){
        boolean witchIsPresent = random.nextBoolean();
        if(witchIsPresent == true){
        return true;
        }else{
        return false;
        }
    }
    @Override
    public void printLocationInfo(){
        String place = getLocaleName();
        
        System.out.println("Your current world location is: " + place + ".");
        System.out.println("You are entering a swampy area. Keep an eye out, as witches are said to inhabit these lands...");
        
        
        
    }
    
}
