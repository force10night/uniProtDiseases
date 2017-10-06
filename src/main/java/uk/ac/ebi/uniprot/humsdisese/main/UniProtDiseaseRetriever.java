package uk.ac.ebi.uniprot.humsdisese.main;


import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseEntry;

import java.util.List;

public interface UniProtDiseaseRetriever {

    public List<HumDiseaseEntry> retrieveData();

}
