package uk.ac.ebi.uniprot.humsdisese.main;

import uk.ac.ebi.uniprot.humsdisease.model.DiseaseKeyType;
import uk.ac.ebi.uniprot.humsdisease.model.Diseases;
import uk.ac.ebi.uniprot.humsdisease.model.DiseasesContainer;
import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseFileReader;
import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseFileReaderImpl;
import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseParser;
import uk.ac.ebi.uniprot.humsdisease.parser.HumDiseaseParserImpl;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class HumsDiseaseGuiceModule extends AbstractModule {

    private String diseasesFile;
    private DiseaseKeyType diseaseType;
    private String release;

    public HumsDiseaseGuiceModule(DiseaseKeyType type, String diseaseListName, String release) {
        super();
       
        this.diseaseType = type;
        this.diseasesFile = diseaseListName;
        this.release = release;
    }

    @Override
    protected void configure() {
        this.bindConstant().annotatedWith(Names.named("DiseaseKeyType")).to(this.diseaseType);
        this.bindConstant().annotatedWith(Names.named("UniProtRelease")).to(this.release);

        
        this.bindConstant().annotatedWith(Names.named("DiseaseFilename")).to(this.diseasesFile);

        
        bind(HumDiseaseFileReader.class).annotatedWith(Names.named("HumdiseaseReader")).to(
                HumDiseaseFileReaderImpl.class);
        bind(HumDiseaseParser.class).annotatedWith(Names.named("HumdiseaseParser")).to(HumDiseaseParserImpl.class);

        bind(UniProtDiseaseRetriever.class).annotatedWith(Names.named("UniProtDiseaseRetriever")).to(UniProtDiseaseRetrieverImpl.class);

        bind(Diseases.class).to(DiseasesContainer.class);
        
      }

}
