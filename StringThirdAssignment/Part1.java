/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            if((currIndex - startIndex)%3 == 0){
                return currIndex;
            }else{
                currIndex = dna.indexOf(stopCodon,currIndex+1);
            }
        }
        return -1;
    }
    
    public String findGene(String dna, int startIndex){
        startIndex = dna.indexOf("atg",startIndex);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "taa");
        int tgaIndex = findStopCodon(dna, startIndex, "tga");
        int tagIndex = findStopCodon(dna, startIndex, "tag");
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while( true ){
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()){
                break;
            }
            geneList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return geneList;
    }
    
    public void testGene(){
        String dna = "AATGCTAACTAGCTGACTAATATGTAA";
        System.out.println("DNA is : " + dna);
        System.out.println("Genes : ");
        StorageResource geneList = getAllGenes(dna.toLowerCase());
        for(String s: geneList.data()){
            System.out.println(s);
        }
        processGenes(geneList);
    }
    
    public float cgRatio(String dna){
        int count = 0;
        for(int index=0; index<dna.length(); index++){
            if(dna.charAt(index) == 'c' || dna.charAt(index) == 'g'){
                count++;
            }
        }
        return (float)count/dna.length();
    }
    
    public void test(){
        String str = "ATGCCATAG";
        System.out.println("String : " + str);
        float cgFrac = cgRatio(str);
        System.out.println(cgFrac);
        str = "ATGCTGCTG";
        System.out.println("String : " + str);
        int ctgNum = countCTG(str);
        System.out.println("CTG present : " + ctgNum);
    }
    
    public int countCTG(String dna){
        int count = 0;
        int currIndex = dna.indexOf("ctg");
        while(currIndex != -1){
            count++;
            currIndex = dna.indexOf("ctg", currIndex+3);
        }
        return count;
    }
    
    public void processGenes(StorageResource sr){
        //Count number of genes having longer than 9 char
        int count1 = 0;
        //Count number of genes having cgRatio > 0.35
        int count2 = 0;
        //Print all string in sr whose length longer than 9 char
        for(String s : sr.data()){
            if(s.length() > 60){
                System.out.println(s);
                count1++;
            }
        }
        System.out.println("Number of Genes longer than 60 char : " + count1);
        for(String s: sr.data()){
            float ratio = cgRatio(s);
            if(ratio > 0.35){
                System.out.println(ratio);
                count2++;
            }
        }
        System.out.println("Number of Genes with cgRatio > 0.35 : " + count2);
        int length = 0;
        for(String s: sr.data()){
            if(s.length() > length){
                length = s.length();
            }
        }
        System.out.println("Longest Gene has length : " + length);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println("CTG present : " + countCTG(dna.toLowerCase()) + " times.");
        StorageResource sr = getAllGenes(dna.toLowerCase());
        processGenes(sr);
        int count = 0;
        for(String s : sr.data()){
            count++;
        }
        System.out.println("Number of genes : " + count);
        
    }
}
