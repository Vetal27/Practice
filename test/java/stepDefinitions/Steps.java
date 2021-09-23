package stepDefinitions;

import apiTests.films.FilmEndpoints;
import apiTests.films.Films;
import apiTests.planets.PlanetEndpoints;
import apiTests.planets.Planets;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class Steps {
    private final String COUNT = "count";
    private final String RESULTS = "results";

    @Given("A base URL and click films")
    public Response openBaseURLAndClickFilms() {
        FilmEndpoints film = new FilmEndpoints();
        return film.getFilms();
    }

    @When("User get films count")
    public int getFilmsCount() {
        JsonPath jsonPathEvaluator = openBaseURLAndClickFilms().jsonPath();
        return jsonPathEvaluator.get(COUNT);
    }

    @Then("User check films count equals to {int}")
    public void checkFilmsCount(int count) {
        Assert.assertEquals(getFilmsCount(), count);
    }

    @When("User get list of films")
    public List<Films> getListOfFilms() {
        JsonPath jsonPathEvaluator = openBaseURLAndClickFilms().jsonPath();
        return jsonPathEvaluator.getList(RESULTS, Films.class);
    }

    @And("User get first film")
    public Films getFirstFilm() {
        return getListOfFilms().get(0);
    }

    @And("User get director of first film")
    public String getDirectorOfFirstFilm() {
        return getFirstFilm().getDirector();
    }

    @Then("User check that first director equals {string}")
    public void checkFirstDirector(String director) {
        Assert.assertEquals(getDirectorOfFirstFilm(), director);
    }

    @Given("A base URL and click planets")
    public Response openBaseURLAndClickPlanets() {
        PlanetEndpoints planets = new PlanetEndpoints();
        return planets.getPlanets();
    }

    @When("User get list of planets")
    public List<Planets> getListOfPlanets() {
        JsonPath jsonPathEvaluator = openBaseURLAndClickPlanets().jsonPath();
        return jsonPathEvaluator.getList(RESULTS, Planets.class);
    }

    @And("User choose planets with diameter greater than {int}")
    public List<Planets> getListOfPlanetsWithGreaterDiameter(int diameter) {
        return getListOfPlanets().stream()
                .filter(x -> Integer.parseInt(x.getDiameter()) > diameter)
                .collect(Collectors.toList());
    }

    @Then("User check that count of result planets with diameter greater than {int} equals {int}")
    public void checkCountOfResultPlanets(int diameter, int count) {
        Assert.assertEquals(getListOfPlanetsWithGreaterDiameter(diameter).size(), count);
    }

    @And("User choose planets with unknown population")
    public List<Planets> getListOfPlanetsWithUnknownPopulation(){
        return getListOfPlanets().stream()
                .filter(x->x.getPopulation().equals("unknown"))
                .collect(Collectors.toList());
    }
    @Then("User check {string} of first planet with unknown population")
    public void checkNameOFirstPlanetWithUnknownPopulation(String name){
        Assert.assertTrue(getListOfPlanetsWithUnknownPopulation().get(0).getName().contains(name));
    }
}