package uk.ac.ebi.uniprot.humsdisease.model;

import uk.ac.ebi.uniprot.humsdisease.exception.DiseaseSearchTypeException;
import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseEntry;

import uk.ac.ebi.uniprot.humsdisese.main.UniProtDiseaseRetriever;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiseasesContainer implements Diseases {

    private UniProtDiseaseRetriever diseaseRetriever;
    private List<HumDiseaseEntry> uniprotDiseases;
    private static Logger LOG = LoggerFactory.getLogger(DiseasesContainer.class);
    private DiseaseKeyType type;
    private Map<String, HumDiseaseEntry> diseaseMap = new HashMap<>();

    @Inject
    public DiseasesContainer(@Named("UniProtDiseaseRetriever") UniProtDiseaseRetriever retriever,
        @Named("DiseaseKeyType") DiseaseKeyType diseaseKeyType) {
        diseaseRetriever = retriever;
        type = diseaseKeyType;
        retrieveDiseases();
        createDiseaseMap();

    }

    @Override
    public HumDiseaseEntry findDisease(String searchKey) {
        if (this.type.compareTo(DiseaseKeyType.ABBREVIATION) == 0) {
            return findByAbbreviation(searchKey);
        } else if (this.type.compareTo(DiseaseKeyType.OMIM) == 0) {
            return findByOmim(searchKey);
        } else {
            throw new DiseaseSearchTypeException("Unknown disease key type");
        }
    }

    /**
     *
     * @param abbreviation
     * @return a UniProtDisease instance or null (must test for the existence of a null object)
     */
    public HumDiseaseEntry findByAbbreviation(String abbreviation) {
        if (diseaseMap.containsKey(abbreviation)) {
            return diseaseMap.get(abbreviation);
        }
        return null;
    }

    /**
     *
     * @param omim
     * @return a UniProtDisease instance or null (must test for the existence of a null object)
     */
    @Override
    public HumDiseaseEntry findByOmim(String omim) {
        if (diseaseMap.containsKey(omim)) {
            return diseaseMap.get(omim);
        }
        return null;
    }

    @Override
    public void setDiseaseSearchKeyType(DiseaseKeyType type) {
        this.type = type;
    }

    @Override
    public Stream<HumDiseaseEntry> unLoad() {
        return this.uniprotDiseases.stream();
    }
    
    @Override
    public String diseaseStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Number of keys = " + diseaseMap.keySet().size());
       return sb.toString();
    }
    
    private void retrieveDiseases() {

        LOG.info("Starting disease retrieval");
        uniprotDiseases = diseaseRetriever.retrieveData();
        LOG.info("Disease definitions stored.");
    }

   
    private  Function<HumDiseaseEntry,Map<String,HumDiseaseEntry>> reMapToOmim = hde -> {
        
        Map<String,HumDiseaseEntry> deMap = new HashMap<>();
        hde.getOmim().forEach(k -> deMap.put(k, hde));
        
        return deMap;  
    };
    
    private void createDiseaseMap() {
        if (this.type.compareTo(DiseaseKeyType.ABBREVIATION) == 0) {

            diseaseMap = uniprotDiseases.stream()
                    .collect(Collectors.toMap(HumDiseaseEntry::getAcronym, d -> d));

            LOG.info("Total number of diseases mapped = " + diseaseMap.keySet().size());
        } else {
            
            LOG.info("Total number of diseases mapped = " + uniprotDiseases.size());
           for (HumDiseaseEntry humDiseaseEntry : uniprotDiseases) {
               diseaseMap.putAll(reMapToOmim.apply(humDiseaseEntry));
           }         
        }

    }
    
    
    
    
}
