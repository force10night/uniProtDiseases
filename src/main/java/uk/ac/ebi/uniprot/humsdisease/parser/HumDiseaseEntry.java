package uk.ac.ebi.uniprot.humsdisease.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HumDiseaseEntry {

    private String id;
    private String accession;
    private String acronym;
    private String definition;
    private List<String> altNames;
    private Map<String, List<String>> xRefs;
    private List<String> keyWords;
    private final static String nullVal = "-";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        if (this.definition == null) {
            this.definition = definition;
        } else {
            this.definition = this.definition.concat(" " + definition);
        }
    }

    public Optional<List<String>> getAltNames() {
        return Optional.ofNullable(altNames);
    }

    public void setAltNames(String altNames) {
        if (this.altNames == null) {
            this.altNames = new ArrayList<String>();
        }

        this.altNames.add(altNames);
    }

    public Map<String, List<String>> getxRefs() {
        return xRefs;
    }

    public void setxRefs(String key, String xRef) {
        Map<String, List<String>> xRefMap;
        List<String> xRefVals;

        if (this.xRefs == null) {
            xRefMap = new HashMap<String, List<String>>();
            xRefVals = new ArrayList<String>();
            xRefVals.add(xRef);
        } else {
            xRefMap = this.xRefs;
            xRefVals = xRefMap.get(key);
            if (xRefVals == null) {
                xRefVals = new ArrayList<String>();
            }
            xRefVals.add(xRef);
        }

        xRefMap.put(key, xRefVals);
        this.xRefs = xRefMap;
    }

    public Optional<List<String>> getKeyWords() {
        return Optional.ofNullable(keyWords);
    }

    public void setKeyWords(String keyWords) {
        if (this.keyWords == null) {
            this.keyWords = new ArrayList<String>();
        }

        this.keyWords.add(keyWords);
    }

    
 
    
    public List<String> getOmim() {
       
        return xRefs.get("MIM");
    }
    
    public String getNullVal() {
        return nullVal;
    }

}
