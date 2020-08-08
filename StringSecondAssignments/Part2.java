import lang.stride.*;

/**
 * 
 */
public class Part2
{
    public int howMany(String stringa, String stringb){
        int countOccur = 0;
        int tempIndex = stringb.indexOf(stringa);
        while( tempIndex != -1 ){
            countOccur++;
            tempIndex = stringb.indexOf(stringa, tempIndex + stringa.length());
        }
        return countOccur;
    }
    
    public void testHowMany(){
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println("Occurence of " + stringa + " in " + stringb + " is " + howMany(stringa, stringb) + " times.");

        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println("Occurence of " + stringa + " in " + stringb + " is " + howMany(stringa, stringb) + " times.");

    }
}
