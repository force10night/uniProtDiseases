package uk.ac.ebi.uniprot.humsdisease.model;

import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseEntry;


import java.util.stream.Stream;

public interface Diseases {

    public HumDiseaseEntry findDisease(String searchKey);

    public void setDiseaseSearchKeyType(DiseaseKeyType type);

    Stream<HumDiseaseEntry> unLoad();

    HumDiseaseEntry findByOmim(String omim);

    String diseaseStatistics();

}
