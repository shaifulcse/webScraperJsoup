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
    Document chairPage = getPage(url);
        Element academicTable = chairPage.select("table").get(0);
        Elements academicTableRows = academicTable.select("tr");
        for (int i = 0; i< academicTableRows.size(); i++){
            Element academicTableRow = academicTableRows.get(i);
            //the role of each person is stated in the first column of the row
            Element rowRole = academicTableRow.select("td").get(0);
            role = rowRole.ownText();
           if(role.toLowerCase().contains("chair")){
               //select the second column of the row, which has the name and email of the person
               Element nameEmail = academicTableRow.select("td").get(1);
               Element nameElement = nameEmail.select("a").get(0);
               name = nameElement.ownText();
               //the email is the last element in the column, so chose to select it at size() -1.
               //did not use index of 1 because the very first row has an extra invisible element at index 1.
               Element emailElement = nameEmail.select("a").get(nameEmail.select("a").size() -1);
               email = emailElement.ownText();
               chairs.add(new Chair(name, role, email));
            }
        }
        return chairs;
    }
    public List<ResearchLab> findResearchLabs(String url) {

        List<ResearchLab> researchLabs = new ArrayList<>();
        Document researchPage = getPage(url);

        //researchPage.select("ul") gets all the unordered lists on the page, and the list of labs is
        //ten items from the bottom of the page
        Element labsList = researchPage.select("ul").get(researchPage.select("ul").size() -10);
        Elements labsListItems = labsList.select("li");
        for(int i = 0; i< labsListItems.size(); i++){
            String labName = labsListItems.get(i).select("a").get(0).ownText();
            String labLink = labsListItems.get(i).select("a").get(0).attr("abs:href");
            researchLabs.add(new ResearchLab(labName, labLink));
        }

        return researchLabs;
    }

    private Document getPage(String url){
        Document returnPage = null;
        try {
            returnPage = Jsoup.connect(url).get();
        }catch (IOException e){
            System.out.println(("Error connecting to " + url));
        }
        return returnPage;
    }

}
