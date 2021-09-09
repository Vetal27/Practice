package apiTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void checkFilmsCount() {
        RestAssured.baseURI = "https://swapi.dev/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/films");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.get("count");
        System.out.println("Count: " + count);
        Assert.assertEquals(count, 6);
    }

    @Test
    public void checkDirectorOfFirstFilm() {
        RestAssured.baseURI = "https://swapi.dev/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/films");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Result> allResults = jsonPathEvaluator.getList("results", Result.class);
        Result firstFilm = allResults.get(0);
        String firstDirector = firstFilm.getDirector();
        System.out.println("Director: " + firstDirector);
        Assert.assertEquals(firstDirector, "George Lucas");
    }

    @Test
    public void checkDiameterOfPlanets() {
        RestAssured.baseURI = "https://swapi.dev/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/planets");
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Planets> allPlanets = jsonPathEvaluator.getList("results", Planets.class);
        List<Planets> resultPlanets = allPlanets.stream()
                .filter(x -> Integer.parseInt(x.getDiameter()) > 10000)
                .peek(x -> System.out.println(x.getName()))
                .collect(Collectors.toList());
        Assert.assertEquals(resultPlanets.size(), 7);
    }

    @Test
    public void checkUnknownPopulationOfPlanets() {
        RestAssured.baseURI = "https://swapi.dev/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/planets");
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Planets> allPlanets = jsonPathEvaluator.getList("results", Planets.class);
        List<Planets> allResultPlanets = allPlanets.stream()
                .filter(x->x.getPopulation().equals("unknown"))
                .collect(Collectors.toList());
        Assert.assertTrue(allResultPlanets.get(0).getName().contains("Hoth"));
    }
}
