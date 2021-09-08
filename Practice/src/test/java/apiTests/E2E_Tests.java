package apiTests;

import java.util.List;
import java.util.Map;

import lombok.Data;
import org.json.simple.JSONObject;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

public class E2E_Tests {

    @Test
    public void CheckFilmsCount()
    {
        RestAssured.baseURI ="https://swapi.dev/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/films");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.get("count");
        System.out.println("Count: " + count);
        Assert.assertEquals(count, 6);
    }

    @Test
    public void CheckDirectorOfFirstFilm()
    {
        RestAssured.baseURI ="https://swapi.dev/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/films");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Result> allResults = jsonPathEvaluator.getList("results", Result.class);
        Result firstFilm = allResults.get(0);
        String firstDirector = firstFilm.getDirector();
        System.out.println("Director: " + firstDirector);
        Assert.assertEquals(firstDirector, "George Lucas");
    }

}
