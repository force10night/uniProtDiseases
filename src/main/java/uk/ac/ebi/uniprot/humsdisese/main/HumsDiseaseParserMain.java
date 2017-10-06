package uk.ac.ebi.uniprot.humsdisese.main;

import uk.ac.ebi.uniprot.humsdisease.exception.DataServiceConfigurationException;
import uk.ac.ebi.uniprot.humsdisease.model.DiseaseKeyType;
import uk.ac.ebi.uniprot.humsdisease.model.Diseases;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.time.Duration;
import java.time.LocalTime;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumsDiseaseParserMain {
    private static Logger LOG = LoggerFactory
            .getLogger(HumsDiseaseParserMain.class);
  
    public static void main(String[] args) {
        LOG.info("validating main arguments:" + args);
        final HumsDiseaseConfigure configure = HumsDiseaseConfigureImpl
                .parseCommandLine(args);

        if (configure.getHelp()) {
            LOG.info(configure.getUsage());
            throw new DataServiceConfigurationException("Invalid number of command line arguments. Program will exit.");
        }

        else if (args.length < 4) {
            LOG.info("invalid number of arguments.  Current number of arguments is " + args.length
                    + " should be 4");
            LOG.info(configure.getUsage());
            throw new DataServiceConfigurationException("Invalid number of command line arguments. Program will exit.");
        }
        Injector injector =
                Guice.createInjector(new HumsDiseaseGuiceModule( DiseaseKeyType.OMIM, configure
                                .getDiseaseFullPath(),configure.getRelease()));
        
        
        
        
        LocalTime startTime = LocalTime.now();
        LOG.info("Starting to build document at:" + startTime.toString());

        Diseases diseases = injector.getInstance(Diseases.class);
        
        // Here is where you can unload in to Mongo
        diseases.unLoad().collect(Collectors.toList());


        LocalTime endTime = LocalTime.now();
        Duration duration = Duration.between(startTime, endTime);
        LOG.info("Finish transforming disease document at: " + endTime.toString()
                + " total duration: " + duration.toMinutes() + " minutes.");
        LOG.info("Summary statistics:\n"+diseases.diseaseStatistics());       

    }

    
  
}
