
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    String findSimpleGene(String dna, String startCodon, String stopCodon){
        // gene
        String gene = "";
        // start codon "ATG"
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1){
            return gene;
        }
        // stop codon "TAA"
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        if(stopIndex == -1){
            return gene;
        }
        // gene is not multiple of 3
        if((stopIndex-startIndex)%3 != 0){
            return gene;
        }
        gene = dna.substring(startIndex, stopIndex+3);
        // gene is our result
        return gene;
    }
    
    void testSimpleGene(){
        String dna = "ATGGGTTAAGTC";
        System.out.println("DNA = " + dna);
        String gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("GENE = " + gene);
        
        dna = "gatgctataat";
        System.out.println("DNA = " + dna);
        gene = findSimpleGene(dna, "atg", "taa") ;
        System.out.println("GENE = " + gene);
    }
}
