package org.example;

import java.util.List;

public class Util {

    public static void printResults(List<Chair> chairs, List<ResearchLab> researchLabs){
        System.out.println("\n############## CS, University of Alberta #####################\n");

        System.out.println("###########################");
        System.out.println("Information about the Chairs");
        for (Chair chair: chairs){
            System.out.println(chair.role+ "\t" + chair.name+"\t"+chair.email);
        }
        System.out.println("###########################\n\n");

        System.out.println("###########################");
        System.out.println("Information about the Research Labs");
        for(ResearchLab researchLab: researchLabs){
            System.out.println(researchLab.labName+ "\t" + researchLab.labLink);
        }
        System.out.println("###########################");

    }
}
