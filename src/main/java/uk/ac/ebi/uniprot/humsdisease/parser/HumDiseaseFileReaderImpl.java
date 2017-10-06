package uk.ac.ebi.uniprot.humsdisease.parser;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumDiseaseFileReaderImpl implements HumDiseaseFileReader {

    private static final Logger LOG = LoggerFactory
            .getLogger(HumDiseaseFileReaderImpl.class);
    private BufferedReader humDiseaseReader;

    @Inject
    public HumDiseaseFileReaderImpl(@Named("DiseaseFilename") String filename) {
        try {
            String pathFile = filename;
            LOG.info("File being read: " + pathFile + "\n");
            humDiseaseReader = new BufferedReader(new FileReader(pathFile));
        } catch (Exception e) {
            throw new IllegalArgumentException("File: "
                    + filename + " file cannot be read\n");
        }
    }

    @Override
    public void closeDoFile() {
        if (humDiseaseReader != null) {
            try {
                humDiseaseReader.close();
            } catch (IOException e) {
                LOG.debug(e.getMessage());
            }
        }
    }

    @Override
    public boolean readDoFile(Consumer<String> parseDoLine) {
        try {
            if (humDiseaseReader.ready()) {
                humDiseaseReader.lines().forEachOrdered(parseDoLine);
                return true;
            }
        } catch (IOException e) {
            LOG.debug(e.getMessage());
        }
        return false;
    }

}
