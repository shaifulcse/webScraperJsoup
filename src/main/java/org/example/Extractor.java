package org.example;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extractor {
    public List<Chair> getChairs(String url){

        String role, name, email;
        List<Chair> chairs = new ArrayList<>();
        Document chairPage;
        try{
            chairPage = Jsoup.connect(url).get();
            Element academicTable = chairPage.select("table").get(0);
            Elements academicTableRows = academicTable.select("tr");
            for (int i = 0; i< academicTableRows.size(); i++){
                Element academicTableRow = academicTableRows.get(i);
                Element rowRole = academicTableRow.select("td").get(0);
                role = rowRole.ownText();
               if(role.contains("chair") || role.contains("Chair") ){
                    Element nameEmail = academicTableRow.select("td").get(1);
                    Element nameElement = nameEmail.select("a").get(0);
                    name = nameElement.ownText();
                    Element emailElement = nameEmail.select("a").get(nameEmail.select("a").size() -1);
                    email = emailElement.ownText();
                    chairs.add(new Chair(name, role, email));
                }
            }
        }catch(IOException e){
            System.out.println("Error connecting to the chair webpage");
        }
        return chairs;
    }
    public List<ResearchLab> findResearchLabs(String url) {

        List<ResearchLab> researchLabs = new ArrayList<>();
        Document researchPage;
        try{
            researchPage = Jsoup.connect(url).get();
            Element labsList = researchPage.select("ul").get(researchPage.select("ul").size() -10);
            Elements labsListItems = labsList.select("li");
           for(int i = 0; i< labsListItems.size(); i++){
               String labName = labsListItems.get(i).select("a").get(0).ownText();
               String labLink = labsListItems.get(i).select("a").get(0).attr("abs:href");
               researchLabs.add(new ResearchLab(labName, labLink));
            }
        }catch(IOException e){
            System.out.println("Error connecting to the research webpage");
        }
        return researchLabs;
    }

}
