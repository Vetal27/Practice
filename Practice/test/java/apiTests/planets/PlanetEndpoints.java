package apiTests.planets;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlanetEndpoints {
    private static final String BASE_URL = "https://swapi.dev/api";

    public Response getPlanets() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification requestSpecification = RestAssured.given();
        return (Response) requestSpecification.header("Content-Type", "application/json").get("/planets");
    }
}
