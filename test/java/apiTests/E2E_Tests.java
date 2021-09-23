package apiTests;

import java.util.List;
import java.util.stream.Collectors;

import apiTests.films.FilmEndpoints;
import apiTests.films.Films;
import apiTests.planets.PlanetEndpoints;
import apiTests.planets.Planets;
import org.junit.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

public class E2E_Tests {

    @Test
    public void checkFilmsCount() {
        FilmEndpoints planets = new FilmEndpoints();
        Response response = planets.getFilms();
        JsonPath jsonPathEvaluator = response.jsonPath();
        int count = jsonPathEvaluator.get("count");
        System.out.println("Count: " + count);
        Assert.assertEquals(count, 6);
    }

    @Test
    public void checkDirectorOfFirstFilm() {
        FilmEndpoints planets = new FilmEndpoints();
        Response response = planets.getFilms();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Films> allResults = jsonPathEvaluator.getList("results", Films.class);
        Films firstFilm = allResults.get(0);
        String firstDirector = firstFilm.getDirector();
        System.out.println("Director: " + firstDirector);
        Assert.assertEquals(firstDirector, "George Lucas");
    }

    @Test
    public void checkDiameterOfPlanets() {
        PlanetEndpoints planets = new PlanetEndpoints();
        Response response = planets.getPlanets();
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
    public void checkFirstUnknownPopulationOfPlanets() {
        PlanetEndpoints planets = new PlanetEndpoints();
        Response response = planets.getPlanets();
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Planets> allPlanets = jsonPathEvaluator.getList("results", Planets.class);
        List<Planets> allResultPlanets = allPlanets.stream()
                .filter(x->x.getPopulation().equals("unknown"))
                .collect(Collectors.toList());
        Assert.assertTrue(allResultPlanets.get(0).getName().contains("Hoth"));
    }
}
