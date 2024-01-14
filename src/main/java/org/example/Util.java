package org.example;

import java.util.List;

public class Util {

    public static void printResults(List<Chair> chairs, List<ResearchLab> researchLabs){
        System.out.println("\n                                    ############## CS, University of Alberta #####################\n");

        System.out.println("                                    ################################################################");
        System.out.printf("%80s\n\n", "INFORMATION ABOUT THE CHAIRS");
        for (Chair chair: chairs){
            System.out.printf("%-50s\t%-30s\t%-20s\n", chair.role, chair.name, chair.email);
        }
        System.out.println("                                    ################################################################\n\n");

        System.out.println("                                    ################################################################");
        System.out.printf("%80s\n\n", "INFORMATION ABOUT THE RESEARCH LABS");
        for(ResearchLab researchLab: researchLabs){
            System.out.printf("%-40s\t%s\n", researchLab.labName, researchLab.labLink);
        }
        System.out.println("\n                                    ################################################################");

    }
}
