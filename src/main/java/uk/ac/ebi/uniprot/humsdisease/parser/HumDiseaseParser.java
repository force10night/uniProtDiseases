package uk.ac.ebi.uniprot.humsdisease.parser;

import java.util.List;

public interface HumDiseaseParser {
    public void ParseLines(String huLine);

    public List<HumDiseaseEntry> getHumDiseaseEntries();

    public void setEntries(List<HumDiseaseEntry> entries);
}
