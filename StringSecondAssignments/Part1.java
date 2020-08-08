
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if((stopIndex - startIndex)%3 == 0){
            return stopIndex;
        }
        return dna.length();
    }
    
    public String findGene(String dna, int startIndex){
    //public String findGene(String dna){
    //    int startIndex = dna.indexOf("ATG");
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
    
    public void printAllGenes(String dna){
        int startIndex = dna.indexOf("ATG");
        while( true ){
            String gene = findGene(dna, startIndex);
            if(gene.isEmpty()){
                break;
            }
            System.out.println("The gene is : " + gene);
            startIndex = dna.indexOf("ATG", startIndex + gene.length());
        }
    }
    
    public void testFindGene(){
        String dna = "AATGCTAACTAGCTGACTAAT";
        //String gene = findGene(dna);
        System.out.println("DNA is : " + dna);
        printAllGenes(dna);
        //System.out.println("GENE is : " + gene);
        
        //dna = "TAA";
        //gene = findGene(dna);
        //System.out.println("DNA is : " + dna);
        //System.out.println("GENE is : " + gene);
        
        //dna = "ATGTAGTGATAA";
        //gene = findGene(dna);
        //System.out.println("DNA is : " + dna);
        //System.out.println("GENE is : " + gene);
    }
    
    public void testFindStopCodon(){
        String dna = "ATGTAATAGTGA";
        int startIndex = dna.indexOf("ATG");
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        System.out.println("The dna is : " + dna);
        System.out.println("The TAG present at : " + tagIndex);
        System.out.println("The TAA present at : " + taaIndex);
        System.out.println("The TGA present at : " + tgaIndex);
        
        dna = "ATG";
        startIndex = dna.indexOf("ATG");
        taaIndex = findStopCodon(dna, startIndex, "TAA");
        tagIndex = findStopCodon(dna, startIndex, "TAG");
        tgaIndex = findStopCodon(dna, startIndex, "TGA");
        System.out.println("The dna is : " + dna);
        System.out.println("The ATG present at : " + tagIndex);
        System.out.println("The TAA present at : " + taaIndex);
        System.out.println("The TGA present at : " + tgaIndex);
        
    }
}
