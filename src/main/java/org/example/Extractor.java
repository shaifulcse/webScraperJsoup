package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extractor {
    static String baseUrl = "https://www.ualberta.ca/computing-science/research/";
    public List<Chair> getChairs(String url){

        String role, name, email;
        List<Chair> chairs = new ArrayList<>();
        Document document = getAPage(url);
        Element table = document.getElementsByTag("table").get(0);

        for (Element element: table.getElementsByTag("tr")){
            if(element.text().toLowerCase().contains("chair")){
               role = element.getElementsByTag("td").get(0).text();
               name = element.getElementsByTag("td").get(1)
                      .getElementsByTag("strong").get(0).text();
               email = element.getElementsByTag("td").get(1)
                       .getElementsByAttribute("href").get(1).text();
               if(email.length() == 0){
                   email = element.getElementsByTag("td")
                           .get(1).getElementsByAttribute("href").get(2).text();
               }
               Chair chair = new Chair(name, role, email);
               chairs.add(chair);

            }
        }
        return chairs;
    }
    public List<ResearchLab> findResearchLabs(String url) {

        List<ResearchLab> researchLabs = new ArrayList<>();
        Document document = getAPage(url);
        Element element = document.getElementsByClass("col-12 col-sm").get(0);
        Element researchAreas = element.getElementsByTag("ul").get(0);

        for (Element area: researchAreas.getElementsByTag("li")){
            String labName = area.select("a").text();
            String labLink = baseUrl + area.select("a").attr("href");
            ResearchLab researchLab = new ResearchLab(labName, labLink);
            researchLabs.add(researchLab);
        }
        return researchLabs;
    }

    public Document getAPage(String url){
        try{
            Document document = Jsoup.connect(url).get();
            return  document;
        }catch (IOException ioException){
            System.out.println("Link didn't work");
            return null;
        }
    }
}
