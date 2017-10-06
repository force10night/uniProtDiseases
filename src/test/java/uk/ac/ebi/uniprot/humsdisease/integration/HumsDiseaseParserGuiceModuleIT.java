package uk.ac.ebi.uniprot.humsdisease.integration;


import uk.ac.ebi.uniprot.humsdisese.main.*;
import uk.ac.ebi.uniprot.humsdisease.model.DiseaseKeyType;


import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HumsDiseaseParserGuiceModuleIT {

    private static HumsDiseaseConfigure configure;
    private Injector injector;

    @Before
    public void setup() throws Exception {
        String[] args =
                new String[]{"-D", "resources/humdisease.txt", "-UV", "2016_04"};
        configure = HumsDiseaseConfigureImpl
                .parseCommandLine(args);
        
        injector =
                Guice.createInjector(new HumsDiseaseGuiceModule( DiseaseKeyType.OMIM, configure
                                .getDiseaseFullPath(),configure.getRelease()));
        
        
    }

    
    @Test
    public void testConfigure() {
        assertEquals(UniProtDiseaseRetriever.class, injector.getInstance(UniProtDiseaseRetriever.class)
                .getClass());
    }   

}
