
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        /*countryInfo(parser, "Nauru");
        parser = fr.getCSVParser();*/
        listExportersTwoProducts(parser, "gold", "diamonds");
        /*parser = fr.getCSVParser();
        int result = numberOfExporters(parser, "cocoa");
        System.out.println(result);
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");*/
    }

    public void countryInfo(CSVParser parser, String country){
        boolean found = true;
        for(CSVRecord record : parser){
            String countryName = record.get("Country");
            if(countryName.contains(country)){
                found = false;
                String exports = record.get("Exports");
                String values = record.get("Value (dollars)");
                System.out.println(country + ": " + exports + ": " + values);
            }
        } 
        if(found){
            System.out.println("NOT FOUND");
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String valueStr){
        int lengthOfString = valueStr.length();
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if(value.length() > lengthOfString){
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
}
