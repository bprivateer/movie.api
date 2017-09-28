package com.example.movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MovieController {

String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }

    @RequestMapping(path = "/medium-popular-long-name", method = RequestMethod.GET)
    public String getMedium(Model model){

        getMovies().stream()
                .filter(e -> e.getPopularity() >= 30 && e.getPopularity() <= 80)
                .filter(e -> e.getTitle().length() >= 10)


        List<Movie> test = getMovies(API_URL);
        model.addAttribute("movies", getMovies(API_URL));
        return "medium-popular-long-name";
    }

    @RequestMapping(path = "/now-playing", method = RequestMethod.GET)
    public String getNow(Model model){


        List<Movie> test = getMovies(API_URL);
        model.addAttribute("movies", getMovies(API_URL));
        return "now-playing";

    }

    public static List<Movie> getMovies(String route){
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject(route, ResultsPage.class);
        System.out.println(resultsPage.results);
        return resultsPage.results;
    }


}
