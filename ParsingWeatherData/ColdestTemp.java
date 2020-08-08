
/**
 * Write a description of ColdestTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ColdestTemp {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            if(smallestSoFar == null){
                smallestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if((currentTemp < smallestTemp) && (currentTemp != -9999)){
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was "+smallest.get("TemperatureF"));
                            //" at " + smallest.get("TimeEST"));
    }
    
    public File fileWithColdestTemperature(){
        CSVRecord smallestSoFar = null;
        File smallestTempFile = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentFileRow = coldestHourInFile(fr.getCSVParser());
            if(smallestSoFar == null){
                smallestSoFar = currentFileRow;
                smallestTempFile = f;
            }else{
                double currentTemp = Double.parseDouble(currentFileRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if(currentTemp < smallestTemp){
                    smallestSoFar = currentFileRow;
                    smallestTempFile = f;
                }
            }
        }
        return smallestTempFile;
    }
    
    public void testFileWithColdestTemperature(){
        File file = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + file.getName());
        FileResource fr = new FileResource(file);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord currentRow : parser){
            System.out.println(currentRow.get("DateUTC") + " " + currentRow.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRow : parser){
            lowestSoFar = lowestOfTwo(lowestSoFar, currentRow);
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));     
    }
    
        public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = lowestOfTwo(lowestSoFar, currentRow);
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityinManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));             
    }
    
    public CSVRecord lowestOfTwo(CSVRecord lowestSoFar, CSVRecord currentRow){
        String currentHumidity = currentRow.get("Humidity");
        if(lowestSoFar == null){
            if(!currentHumidity.equals("N/A"))
                lowestSoFar = currentRow;
        }else{
            if(!currentHumidity.equals("N/A")){
                int current = Integer.parseInt(currentHumidity);
                int lowest = Integer.parseInt(lowestSoFar.get("Humidity"));
                if(current < lowest){
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sumTemp = 0;
        int count = 0;
        for(CSVRecord record : parser){
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            sumTemp += currentTemp;
            count++;
        }
        return (sumTemp/count);
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumTemp = 0;
        int count = 0;
        for(CSVRecord record : parser){
            int humidity = Integer.parseInt(record.get("Humidity"));
            if(humidity >= value){
                count++;
                double temp = Double.parseDouble(record.get("TemperatureF"));
                sumTemp += temp;
            }
        }
        return (sumTemp/count);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if(avgTemp == Double.NaN){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }
    }
}
