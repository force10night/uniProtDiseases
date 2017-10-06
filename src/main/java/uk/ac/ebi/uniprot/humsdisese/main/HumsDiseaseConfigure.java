package uk.ac.ebi.uniprot.humsdisese.main;



public interface HumsDiseaseConfigure {

    public String getUsage();
    public boolean getHelp();

    public String getDiseaseFullPath();

    public String getRelease();

    

    boolean validate();

  
}
