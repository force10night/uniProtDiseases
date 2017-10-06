package uk.ac.ebi.uniprot.humsdisease.parser;

import java.util.function.Consumer;

public interface HumDiseaseFileReader {

    public void closeDoFile();

    public boolean readDoFile(Consumer<String> parseDoLine);

}
