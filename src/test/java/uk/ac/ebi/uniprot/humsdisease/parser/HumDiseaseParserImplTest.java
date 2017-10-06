package uk.ac.ebi.uniprot.humsdisease.parser;


import org.junit.Test;


public class HumDiseaseParserImplTest {

    
    
    
    @Test
    public void testDRSplit() {
        String[] e = "MedGen; C3148823.".split("; ");
        System.out.println(e[0] + " " + e[1]);

        String[] d = "DR    MedGen; C3148823.".split("\\s{2,}");
        System.out.println(d[0]); // + " " + d[1]

    }

}
