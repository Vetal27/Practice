package apiTests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private String title;
    @JsonProperty("episode_id")
    private int episodeId;
    @JsonProperty("opening_crawl")
    private String openingCrawl;
    private String director;
    private String producer;
    @JsonProperty("release_date")
    private String releaseDate;
    private List<String> characters;
    private List<String> planets;
    private List<String> starships;
    private List <String> vehicles;
    private List<String> species;
    private String created;
    private String edited;
    private String url;
}
