
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if((stopIndex - startIndex)%3 == 0){
            return stopIndex;
        }
        return dna.length();
    }
    
    public String findGene(String dna, int startIndex){
        //int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        // int temp = Math.min(taaIndex, tgaIndex);
        // int minIndex = Math.min(tagIndex, temp);
        int minIndex = Math.min(tagIndex, Math.min(taaIndex, tgaIndex));
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public int countGenes(String dna){
        int countGene = 0;
        int startIndex = dna.indexOf("ATG");
        String gene = findGene(dna, startIndex);
        while( !gene.isEmpty() ){
            countGene++;
            startIndex = dna.indexOf("ATG", startIndex + gene.length());
            gene = findGene(dna, startIndex);
        }
        return countGene;
    }
    
    void testCountGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("Number of genes in " + dna + " is " + countGenes(dna) + ".");
    }
}
