package uk.ac.ebi.uniprot.humsdisese.main;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.CommaParameterSplitter;

public class HumsDiseaseConfigureImpl implements HumsDiseaseConfigure {

   

    @Parameter(names = "-D", description = "disease definitions files full path")
    private String diseaseFullPath = "";

    @Parameter(names = "-UV", description = "UniProt release version eg 2016_04")
    private String release = "";

   

    @Parameter(names = "--help", help = true)
    private boolean help;
    private JCommander jCommander;

    private HumsDiseaseConfigureImpl() {}

    public static final HumsDiseaseConfigure parseCommandLine(String[] args) {
        HumsDiseaseConfigureImpl configurator = new HumsDiseaseConfigureImpl();
        configurator.jCommander = new JCommander(configurator, args);
        return configurator;
    }

    @Override
    public String getUsage() {
        StringBuilder out = new StringBuilder();
        jCommander.usage(out);
        return out.toString();
    }

  

    @Override
    public boolean getHelp() {
        return help;
    }

    @Override
    public String getDiseaseFullPath() {
        return diseaseFullPath;
    }

    @Override
    public final String getRelease() {
        return release;
    }

    public final void setRelease(String release) {
        this.release = release;
    }

   
    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    

}
