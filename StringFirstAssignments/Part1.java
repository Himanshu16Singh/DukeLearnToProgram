
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    String findSimpleGene(String dna){
        // gene
        String gene = "";
        // start codon "ATG"
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return gene;
        }
        // stop codon "TAA"
        int stopIndex = dna.indexOf("TAA",startIndex+3);
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
        String dna = "TGAATG";
        System.out.println("DNA = " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("GENE = " + gene);
        
        dna = "AATTAA";
        System.out.println("DNA = " + dna);
        gene = findSimpleGene(dna) ;
        System.out.println("GENE = " + gene);
        
        dna = "CTGATGGCTTAA";
        System.out.println("DNA = " + dna);
        gene = findSimpleGene(dna) ;
        System.out.println("GENE = " + gene);
        
        dna = "CTTATGGGCCCTAA";
        System.out.println("DNA = " + dna);
        gene = findSimpleGene(dna) ;
        System.out.println("GENE = " + gene);
    }
}
