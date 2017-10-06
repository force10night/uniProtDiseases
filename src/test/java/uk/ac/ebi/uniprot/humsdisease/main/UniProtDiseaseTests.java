package uk.ac.ebi.uniprot.humsdisease.main;

import uk.ac.ebi.uniprot.humsdisese.main.UniProtDisease;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniProtDiseaseTests {

    
    @Before
    public void setUp() throws Exception {

        UniProtDisease d1 = new UniProtDisease("Crouzon syndrome (CS)", "CS", "MIM:123500");
        UniProtDisease d2 = new UniProtDisease("Jackson-Weiss syndrome (JWS)", "JWS", "MIM:123150");
        UniProtDisease d3 = new UniProtDisease("Pfeiffer syndrome (PS)", "PS", "MIM:101600");
        UniProtDisease d4 =
                new UniProtDisease(
                        "Antley-Bixler syndrome, without genital anomalies or disordered steroidogenesis (ABS2)",
                        "ABS2", "MIM:207410");

        }
    
    
    
    @Test
    public void testText() {
        UniProtDisease dis = new UniProtDisease("Microphthalmia, syndromic, 12", "MCOPS12", "MIM:615524");
     //   dis.setAbbreviation("MCOPS12");
     //   dis.setDiseaseName("Microphthalmia, syndromic, 12");
    //    dis.setOmim("MIM:615524");
        assertEquals("MCOPS12", dis.getAbbreviation());
        assertEquals("MIM:615524", dis.getOmim());
        assertEquals("Microphthalmia, syndromic, 12", dis.getDiseaseName());

    }

    @Test
    public void testtoLine() {

        UniProtDisease ud = new UniProtDisease("Pfeiffer syndrome.", "PS", "101600");

        String s = UniProtDisease.toLine(ud);
        assertEquals("\"Pfeiffer syndrome (PS)\" \"-\" \"MIM\" \"101600\" \"-\"", s);
    }

}
