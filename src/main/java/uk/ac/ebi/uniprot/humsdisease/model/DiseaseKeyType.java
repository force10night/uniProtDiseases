package uk.ac.ebi.uniprot.humsdisease.model;

public enum DiseaseKeyType {
    OMIM("OMIM"),
    ABBREVIATION("ABBREVIATION");

    private final String diseaseType;

    private DiseaseKeyType(String type) {
        diseaseType = type;
    }

}
