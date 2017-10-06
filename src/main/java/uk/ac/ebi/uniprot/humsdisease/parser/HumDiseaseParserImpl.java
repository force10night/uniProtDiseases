package uk.ac.ebi.uniprot.humsdisease.parser;



import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumDiseaseParserImpl implements HumDiseaseParser {

    private List<HumDiseaseEntry> humDiseaseEntries = new ArrayList<HumDiseaseEntry>();
    private HumDiseaseEntry entry;
    private boolean process;
    private static Logger LOG = LoggerFactory.getLogger(HumDiseaseParserImpl.class);

    @Override
    public void ParseLines(String huLine) {
        if (huLine.startsWith("ID")) {
            entry = new HumDiseaseEntry();
            process = true;
            String[] tagAndValue = huLine.split("\\s{2,}");
            entry.setId(tagAndValue[1]);
        } else if (huLine.startsWith("//") && process) {
            process = false;
            humDiseaseEntries.add(entry);
        } else if (process) {
            String[] tagAndValue = huLine.split("\\s{2,}");
            try {
                if (huLine.startsWith("AC")) {
                    entry.setAccession(tagAndValue[1]);
                } else if (huLine.startsWith("AR")) {
                    entry.setAcronym(HumDiseaseParserImpl.removeFinalFullStop(tagAndValue[1]));
                } else if (huLine.startsWith("DE")) {
                    entry.setDefinition(tagAndValue[1]);
                } else if (huLine.startsWith("SY")) {
                    entry.setAltNames(tagAndValue[1]);
                } else if (huLine.startsWith("DR")) {
                    String[] valuePair = tagAndValue[1].split("; ");
                    entry.setxRefs(valuePair[0], HumDiseaseParserImpl.removeFinalFullStop(valuePair[1]));
                } else if (huLine.startsWith("KW")) {
                    entry.setKeyWords(HumDiseaseParserImpl.removeFinalFullStop(tagAndValue[1]));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
               LOG.debug(e.getMessage());
               LOG.debug("Line that failed: "+huLine);
            }
        }

    }

    @Override
    public List<HumDiseaseEntry> getHumDiseaseEntries() {
        return humDiseaseEntries;
    }

    @Override
    public void setEntries(List<HumDiseaseEntry> ontologies) {
        humDiseaseEntries = ontologies;

    }

    public static String removeFinalFullStop(String text) {
        if (text.endsWith(".")) {
            return text.substring(0, text.length() - 1);
        } else {
            return text;
        }
    }

}
