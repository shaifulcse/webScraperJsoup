package org.example;

import java.io.IOException;
import java.util.List;

public class Main
{
    public static void main( String[] args ) throws IOException {
        Extractor extractor = new Extractor();
        List<Chair> chairs = extractor.getChairs("https://www.ualberta.ca/computing-science/faculty-and-staff/index.html");
        List<ResearchLab> researchLabs = extractor.findResearchLabs("https://www.ualberta.ca/computing-science/research/index.html");
        Util.printResults(chairs, researchLabs);
    }
}
