package uk.ac.ebi.uniprot.humsdisese.main;


import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseEntry;
import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseFileReader;
import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseParser;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniProtDiseaseRetrieverImpl implements UniProtDiseaseRetriever {

    private static Logger LOG = LoggerFactory.getLogger(UniProtDiseaseRetrieverImpl.class);
    private HumDiseaseFileReader reader;
    private HumDiseaseParser parser;
    private List<HumDiseaseEntry> entries = new ArrayList<>();
    private List<UniProtDisease> diseases = new ArrayList<>();

    @Inject
    public UniProtDiseaseRetrieverImpl(@Named("HumdiseaseReader") HumDiseaseFileReader humDiseaseReader,
        @Named("HumdiseaseParser") HumDiseaseParser humDiseaseParser) {
      
        reader = humDiseaseReader;
        parser = humDiseaseParser;

      
    }

    @Override
    public List<HumDiseaseEntry> retrieveData() {

        LOG.info("Executing disease definition retrieval");
        executeDiseaseFileRead();
        
        LOG.info("Disease definitions retrieved");
        return entries;

    }

    private boolean executeDiseaseFileRead() {

        parser.setEntries(entries);

        LOG.info("reading humdisease file.");
        reader.readDoFile(parser::ParseLines);

        return true;
    }

  

   
}
