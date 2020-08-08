
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part4 {
    public void start(){
        String ur = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        URLResource url = new URLResource(ur);
        for(String line : url.lines()){
               //System.out.println(line);
               int pos = line.toLowerCase().indexOf("youtube");
               if (pos != -1) {
                        String quote = "\"";
                        int beg = line.lastIndexOf(quote,pos);
                        int end = line.lastIndexOf(quote);
                        System.out.println(line.substring(beg,end+1));
               }
        }
    }
}
