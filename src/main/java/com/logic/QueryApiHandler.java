package com.logic;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import com.model.News;
import org.junit.Test;


import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;

/**
 *
 */
public class QueryApiHandler {

    private List<HashMap<String, String>> news = new ArrayList<>();

    /**
     * Method that filter validated news according to inputted user data
     * @param filter filter inputed
     * @return list of filtered validated news
     */
    public List<News> getFilteredNews(String filter){
        List<News> filtered = new ArrayList<>();

        for(int i = 0; i < news.size(); i++){
            HashMap<String, String> map = news.get(i);
            News n = new News();
            n.setId("news_id");
            n.setTitle(map.get("title"));
            n.setDescription(map.get("desc"));
            if(n.getTitle().toLowerCase().contains(filter.toLowerCase())
                    || n.getDescription().toLowerCase().contains(filter.toLowerCase()))
            {
                filtered.add(n);
            }
        }
        return filtered;
    }

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void getValidatedNews() {
        Response response = doGetRequest("http://localhost:3000/api/queries/Q1");

        news = response.jsonPath().getList("$");

        System.out.println(news.size());

        /*List<News> filtered = getFilteredNews("lula");
        for(int i = 0; i < filtered.size(); i++) {
            System.out.println(filtered.get(i).getTitle() + "\n"
                    + filtered.get(i).getDescription());
        }*/
    }
}
