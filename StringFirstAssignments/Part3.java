
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    boolean twoOccurences(String stringa, String stringb){
        // occurence index
        int index = stringb.indexOf(stringa);
        if(index == -1){
            return false;
        }
        index = stringb.indexOf(stringa, index+1);
        if(index == -1){
            return false;
        }
        return true;
    }
    void testing(){
        String stringa = "by";
        String stringb = "A story by Abby Long";
        System.out.println("stringa = " + stringa);
        System.out.println("stringb = " + stringb);
        System.out.println("twoOccurences = " + twoOccurences(stringa,stringb));
    
        stringa = "a";
        stringb = "banana";
        System.out.println("stringa = " + stringa);
        System.out.println("stringb = " + stringb);
        System.out.println("twoOccurences = " + twoOccurences(stringa,stringb));
    
        stringa = "atg";
        stringb = "ctgtatgta";
        System.out.println("stringa = " + stringa);
        System.out.println("stringb = " + stringb);
        System.out.println("twoOccurences = " + twoOccurences(stringa,stringb));
        
        stringa = "an";
        stringb = "banana";
        System.out.println("The part of the string after" + stringa 
        + " in " + stringb + " is " + lastPart(stringa, stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("The part of the string after" + stringa 
        + " in " + stringb + " is " + lastPart(stringa, stringb));
    }
    String lastPart(String stringa, String stringb){
        int index = stringb.lastIndexOf(stringa);
        if(index == -1){
            return stringb;
        }
        return stringb.substring(index);
    }
}
