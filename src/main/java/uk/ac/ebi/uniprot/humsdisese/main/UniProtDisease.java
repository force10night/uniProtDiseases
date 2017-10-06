package uk.ac.ebi.uniprot.humsdisese.main;

import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseParserImpl;
import uk.ac.ebi.uniprot.services.feature.jaxb.AssociationType;
import uk.ac.ebi.uniprot.services.feature.jaxb.DbReferenceType;

public class UniProtDisease {

    private final String omim ;
    private final String abbreviation;
    private final String diseaseName;

    public UniProtDisease(String name, String abbreviation, String omim) {
        this.abbreviation = abbreviation;
        this.diseaseName = name;
        this.omim = omim;
    }


    public final String getOmim() {
        return omim;
    }

    public final String getAbbreviation() {
        return abbreviation;
    }

    public final String getDiseaseName() {
        return diseaseName;
    }

    public static AssociationType toAssociationType(UniProtDisease ud) {
        AssociationType association = new AssociationType();

        association.setName(HumDiseaseParserImpl.removeFinalFullStop(ud.getDiseaseName()) + " (" + ud.getAbbreviation()
                + ")");
        association.setIsDisease(true);

        DbReferenceType omim = new DbReferenceType();
        omim.setId(ud.getOmim());
        omim.setType("MIM");
        association.getXrefs().add(omim);

        return association;
    }

    public static String toLine(UniProtDisease ud) {
        return "\"" + HumDiseaseParserImpl.removeFinalFullStop(ud.getDiseaseName()) + " (" + ud.getAbbreviation()
                + ")\" \"-\" \"MIM\" \"" + ud.getOmim() + "\" \"-\"";
    }

}
