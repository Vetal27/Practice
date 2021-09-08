package apiTests;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String produser;
    private String release_date;
    private List<String> characters;
    private List<String> planets;
    private List<String> starships;
    private List <String> vehicles;
    private List<String> species;
    private String created;
    private String edited;
    private String url;
}
